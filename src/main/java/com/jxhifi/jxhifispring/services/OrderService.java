package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.ConvertAddress_To_AddressDTO;
import com.jxhifi.jxhifispring.DTO.Order.*;
import com.jxhifi.jxhifispring.entities.*;
import com.jxhifi.jxhifispring.repositories.OrderRepository;
import com.jxhifi.jxhifispring.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService extends ConvertAddress_To_AddressDTO {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private OrderItemService orderItemService;
    private static long idNumber = 1L;
    private final EntityManager entityManager;


    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    public List<DashboardVersion_OrderDTO> getAllOrdersTable() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::orderToDashboardDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(String id) {
        Order order = orderRepository.findOrderById(id);
        return orderToDTO(order);
    }

    //TODO appelé dans l'update de Order
    public Order getExistingOrder(String id) {
        Order order = orderRepository.findOrderById(id);
        return order;
    }

    public Order updateOrder(String id, OrderDTO oDTO) {
        //Recup l'order existant a partir de l'id
        Order order = orderRepository.findOrderById(id);

        OrderDTO_To_Order(oDTO, order);

        if (oDTO.getCustomer() != null) {
            order.getCustomer().setEmail(oDTO.getCustomer().getEmail());
            order.getCustomer().setPhone(oDTO.getCustomer().getPhone());
        }

        // MàJ des items
        if (oDTO.getOrderItems() != null)
            updateOrderItem(order, oDTO);

        return orderRepository.save(order);
    }

    private void updateOrderItem(Order order, OrderDTO oDTO) {
        // Liste pour suivre les IDs existants
        Set<String> existingIds = new HashSet<>();

        // Mettre à jour ou ajouter les éléments
        for (DashboardDetail_OrderItemDTO dto : oDTO.getOrderItems()) {
            // Trouver l'élément correspondant dans la liste actuelle
            Optional<OrderItem> existingItem = order.getOrderItems().stream()
                    .filter(item -> item.getProduct().getId().equals(dto.getProduct().getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                // Mettre à jour les valeurs si l'élément existe déjà
                OrderItem orderItem = existingItem.get();
                orderItem.setQuantity(dto.getQuantity());
                double subTotal = dto.getSousTotal();
                if (subTotal == 0) {
                    if (dto.getProduct().isOnSale()) {
                        subTotal = dto.getProduct().getSpecialPrice();
                    } else {
                        subTotal = dto.getQuantity() * dto.getProduct().getSellPrice();
                    }
                }
                orderItem.setSubTotal(subTotal);
            } else {
                // Ajouter un nouvel élément si non présent
                OrderItem newItem = convertToOrderItem(dto, order);

                order.getOrderItems().add(newItem);
            }

            // Ajouter l'ID au set d'éléments existants
            existingIds.add(dto.getProduct().getId());
        }

        // Supprimer les éléments qui ne sont plus dans la liste DTO
        order.getOrderItems().removeIf(item -> !existingIds.contains(item.getProduct().getId()));
    }


    //*********************************************** Methode de converstion en DTO ou inversement ***************************************************

    /**
     * Prend un objet OrderDTO et le converti en Order pour la db.
     * @param oDTO
     * @param order
     */
    private static void OrderDTO_To_Order(OrderDTO oDTO, Order order) {
        order.setTPS(oDTO.getTPS());
        order.setStateTax(oDTO.getStateTax());
        order.setTTC(oDTO.getTotalAmount());
        order.setStatus(oDTO.getStatus());
        order.setOrderDate(oDTO.getOrderDate());

        if (oDTO.getCard() != null) {
            order.getCard().setPaymentMethod(oDTO.getCard().getPaymentMethod());
            order.getCard().setCardNumber(oDTO.getCard().getCardNumber());
            order.getCard().setExpiryDate(oDTO.getCard().getExpiryDate());
            order.getCard().setCvc(oDTO.getCard().getCvc());
        }

        if (oDTO.getShippingAddress() != null) {
            order.getShippingAddress().setAddress(oDTO.getShippingAddress().getAddress());
            order.getShippingAddress().setCity(oDTO.getShippingAddress().getCity());
            order.getShippingAddress().setProvince(oDTO.getShippingAddress().getProvince());
            order.getShippingAddress().setPostalCode(oDTO.getShippingAddress().getPostalCode());
            order.getShippingAddress().setCountry(oDTO.getShippingAddress().getCountry());
        }
    }

    private DashboardVersion_OrderDTO orderToDashboardDTO(Order order) {
        DashboardVersion_OrderDTO dto = new DashboardVersion_OrderDTO();

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        double tps = order.getTPS();
        double stateTax = order.getStateTax();
        double total = 0;
        for (OrderItem item : order.getOrderItems()) {
            total += item.getSubTotal();
        }
        if (total > 0) {
            order.setTTC((total * (tps + stateTax) / 100) + total);
            dto.setTotal(order.getTTC());
        }


        //passe au traver chaque OrderItem pour fetch les qty total de chaque produit commander
        dto.setNbrProducts(order.getOrderItems().stream()
                .mapToInt(OrderItem::getQuantity).sum());

        dto.setCustomerId(order.getCustomer().getId());
        dto.setCustomerMail(order.getCustomer().getEmail());

        return dto;
    }

    /**
     * convertion de l'entité order en DTO
     *
     * @param order
     * @return une version tranferable d'une instance de Order
     */
    public OrderDTO orderToDTO(Order order) {
        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setTPS(order.getTPS());
        dto.setStateTax(order.getStateTax());
        dto.setTotalAmount(order.getTTC());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());

        dto.setOrderItems(order.getOrderItems()
                .stream().map(this::convertAllOderItem_ToDTO).collect(Collectors.toList()));

        dto.setCard(convertCardToCardDTO(order.getCard()));

        dto.setShippingAddress(addressToAddressDTO(order.getShippingAddress()));

        dto.setCustomer(customerService.ToDTO(order.getCustomer()));

        return dto;
    }

    /**
     * converti une instance de product en ProductDTO version light (attribut selectionné)
     *
     * @param product
     * @return un produitDTO light pour les modif d'un order
     */
    private DD_OrderItem_ProductDTO productToDDOIProductDTO(Product product) {
        DD_OrderItem_ProductDTO dto = new DD_OrderItem_ProductDTO();
        dto.setId(product.getId());
        dto.setBrand(product.getBrand());
        dto.setName(product.getName());
        dto.setSellPrice(product.getSellPrice());
        dto.setSpecialPrice(product.getSpecialPrice());
        dto.setOnSale(product.isOnSale());
        dto.setStock(product.getStock());
        return dto;
    }

    /**
     * si lors de la modification d'un order un produit est ajout a la list de OrderItem
     * il est convertit en entité pour le back tout en mettant a jours son stock.
     * Aussi valable pour la creation d'un order complet
     *
     * @param dto
     * @param qtyItem
     * @return la version entité du produitDTO venu du front
     */
    private Product productDTOToProduct(DD_OrderItem_ProductDTO dto, int qtyItem) {
        Product product = productRepository.findById(dto.getId()).get();
        // déduction de la quantité en stock du product
        product.setStock(product.getStock() - qtyItem);
        return product;
    }

    /**
     * converti chaque item d'une liste OrderItem pour sa version transferable
     *
     * @param item
     * @return un item pret pour le front
     */
    private DashboardDetail_OrderItemDTO convertAllOderItem_ToDTO(OrderItem item) {
        DashboardDetail_OrderItemDTO dshbdoiDTO = new DashboardDetail_OrderItemDTO();
        //Converti tout le produit en version allege pour le transfert
        dshbdoiDTO.setProduct(productToDDOIProductDTO(item.getProduct()));
        dshbdoiDTO.setQuantity(item.getQuantity());
        dshbdoiDTO.setSousTotal(item.getSubTotal());
        return dshbdoiDTO;
    }

    /**
     * converti Une entité Card pour le tranfert de donnée
     *
     * @param card
     * @return un model de Card pour le front
     */
    private CardDTO convertCardToCardDTO(Card card) {
        CardDTO cDTO = new CardDTO();
        cDTO.setCardNumber(card.getCardNumber());
        cDTO.setCvc(card.getCvc());
        cDTO.setExpiryDate(card.getExpiryDate());
        cDTO.setPaymentMethod(card.getPaymentMethod());
        return cDTO;
    }

    private OrderItem convertToOrderItem(DashboardDetail_OrderItemDTO dto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemService.generateNewId());
        orderItem.setProduct(productDTOToProduct(dto.getProduct(), dto.getQuantity()));
        orderItem.setQuantity(dto.getQuantity());
        double subTotal = dto.getSousTotal();

        if (subTotal == 0) {
            if (dto.getProduct().isOnSale()) {
                subTotal = dto.getProduct().getSpecialPrice();

            } else
                subTotal = dto.getQuantity() * dto.getProduct().getSellPrice();
        }

        orderItem.setSubTotal(subTotal);
        orderItem.setOrder(order);
        return orderItem;
    }

    @PostConstruct
    private void initNumber() {
        Optional<Order> lastOrderOptional = this.orderRepository.findTopByIdNumericPart();
        if (lastOrderOptional.isPresent()) {
            String lastId = lastOrderOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    public List<Order> getAllOrdersFromCustomerId(String id) {
        return orderRepository.getAllOrdersByCustomerId(id);
    }

    public synchronized String generateNewId() {
        String id = "ORD" + idNumber;
        idNumber++;
        return id;
    }

}