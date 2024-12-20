package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.ConvertAddress_To_AddressDTO;
import com.jxhifi.jxhifispring.DTO.Order.*;
import com.jxhifi.jxhifispring.entities.*;
import com.jxhifi.jxhifispring.repositories.CardRepository;
import com.jxhifi.jxhifispring.repositories.OrderRepository;
import com.jxhifi.jxhifispring.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    @Autowired
    private CardService cardService;
    @Autowired
    private AddressService addressService;
    private static long idNumber = 1L;
    private final EntityManager entityManager;
    @Autowired
    private CardRepository cardRepository;


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

        for (DashboardDetail_OrderItemDTO dto : oDTO.getOrderItems()) {
            // Trouver item correspondant
            Optional<OrderItem> existingItem = order.getOrderItems().stream()
                    .filter(item -> item.getProduct().getId().equals(dto.getProduct().getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                // Màj des valeurs deja existantes
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
                OrderItem newItem = convertToOrderItem(dto, order);

                order.getOrderItems().add(newItem);
            }

            // Ajouter l'ID au set d'éléments existants
            existingIds.add(dto.getProduct().getId());
        }

        // Supprime les items qui ont ete removed dans le front
        order.getOrderItems().removeIf(item -> !existingIds.contains(item.getProduct().getId()));
    }
/*
    @Transactional
    public Order createNewOrder(OrderDTO orderDTO) {
        for (DashboardDetail_OrderItemDTO dto : orderDTO.getOrderItems())
            System.out.println(dto.toString());

        Order newOrder = new Order();

        //Genere l'id
        String id = generateNewId();
        System.out.println("id : " + id);
        newOrder.setId(id);

        //mets a jour les attribut propre a la classe order
        OrderDTO_To_Order(orderDTO, newOrder);

        //Fetch le customer lié a la commande
        newOrder.setCustomer(customerService.getCustomer(orderDTO.getCustomer().getId()));

        if (orderDTO.getOrderItems() != null) {
            List<OrderItem> orderItems = newOrder.getOrderItems();
            if (orderItems == null) {
                orderItems = new ArrayList<>();
            }
            //initialise la liste des produit acheté
            for (DashboardDetail_OrderItemDTO dto : orderDTO.getOrderItems()) {
                OrderItem newItem = convertToOrderItem(dto, newOrder);

                newItem = orderItemService.addOrderItem(newItem);
                orderItems.add(newItem);
            }

            newOrder.setOrderItems(orderItems);
        }

        //TODO verifier si la card existe deja dans la db.
        // si oui associer ces deux card
        // sinon creer une nouvelle card
        if (orderDTO.getCard() != null) {
            Card card = newOrder.getCard();
            if (card == null) {
                card = new Card();
            }
            card.setId(cardService.generateNewId());
            card.setPaymentMethod(orderDTO.getCard().getPaymentMethod());
            card.setCardNumber(orderDTO.getCard().getCardNumber());
            card.setExpiryDate(orderDTO.getCard().getExpiryDate());
            card.setCvc(orderDTO.getCard().getCvc());
            card.setNameHolder(orderDTO.getCustomer().getFirstName() + " " + orderDTO.getCustomer().getLastName());

            card = cardService.addCard(card);
            newOrder.setCard(card);
        }

        //TODO verifier si l'adresse shipping et la meme que l'adresse du customer.
        // si oui associer ces deux address
        // sinon creer une nouvelle adresse
        if (orderDTO.getShippingAddress() != null) {
            Address shippingAddress = newOrder.getShippingAddress();
            if (shippingAddress == null) {
                shippingAddress = new Address();
            }
            shippingAddress.setId(addressService.generateId());
            shippingAddress.setAddress(orderDTO.getShippingAddress().getAddress());
            shippingAddress.setCity(orderDTO.getShippingAddress().getCity());
            shippingAddress.setProvince(orderDTO.getShippingAddress().getProvince());
            shippingAddress.setPostalCode(orderDTO.getShippingAddress().getPostalCode());
            shippingAddress.setCountry(orderDTO.getShippingAddress().getCountry());

            shippingAddress = addressService.addAddress(shippingAddress);
            newOrder.setShippingAddress(shippingAddress);
        }


        return orderRepository.save(newOrder);
    }*/

    @Transactional
    public Order createNewOrder(OrderDTO orderDTO) {
        for (DashboardDetail_OrderItemDTO dto : orderDTO.getOrderItems()) {
            System.out.println(dto.toString());
        }

        Order newOrder = new Order();

        // Generate the ID and set it
        String id = generateNewId();
        System.out.println("id : " + id);
        newOrder.setId(id);

        // Update attributes of the order
        OrderDTO_To_Order(orderDTO, newOrder);

        // Fetch the customer and associate it with the order
        newOrder.setCustomer(customerService.getCustomer(orderDTO.getCustomer().getId()));

        // Save the new order BEFORE anything else
        newOrder = orderRepository.save(newOrder);

        // Initialize the list of purchased products
        if (orderDTO.getOrderItems() != null) {
            List<OrderItem> orderItems = newOrder.getOrderItems();
            if (orderItems == null) {
                orderItems = new ArrayList<>();
            }

            for (DashboardDetail_OrderItemDTO dto : orderDTO.getOrderItems()) {
                // Create the order item
                OrderItem newItem = convertToOrderItem(dto, newOrder);

                // Save the order item (it will now reference an already persisted order)
                newItem = orderItemService.addOrderItem(newItem);

                // Add the order item to the order's collection
                orderItems.add(newItem);
            }

            // Update the order items list
            newOrder.setOrderItems(orderItems);
        }

        // Handle card (if necessary)
        if (orderDTO.getCard() != null) {
            Card card = newOrder.getCard();
            if (card == null) {
                card = new Card();
            }
            card.setId(cardService.generateNewId());
            card.setPaymentMethod(orderDTO.getCard().getPaymentMethod());
            card.setCardNumber(orderDTO.getCard().getCardNumber());
            card.setExpiryDate(orderDTO.getCard().getExpiryDate());
            card.setCvc(orderDTO.getCard().getCvc());
            card.setNameHolder(orderDTO.getCustomer().getFirstName() + " " + orderDTO.getCustomer().getLastName());

            card = cardService.addCard(card);
            newOrder.setCard(card);
        }

        // Handle shipping address (if necessary)
        if (orderDTO.getShippingAddress() != null) {
            Address shippingAddress = newOrder.getShippingAddress();
            if (shippingAddress == null) {
                shippingAddress = new Address();
            }
            shippingAddress.setId(addressService.generateId());
            shippingAddress.setAddress(orderDTO.getShippingAddress().getAddress());
            shippingAddress.setCity(orderDTO.getShippingAddress().getCity());
            shippingAddress.setProvince(orderDTO.getShippingAddress().getProvince());
            shippingAddress.setPostalCode(orderDTO.getShippingAddress().getPostalCode());
            shippingAddress.setCountry(orderDTO.getShippingAddress().getCountry());

            shippingAddress = addressService.addAddress(shippingAddress);
            newOrder.setShippingAddress(shippingAddress);
        }

        return newOrder;
    }
    //*********************************************** Methode de converstion en DTO ou inversement ***************************************************


    /**
     * Prend un objet OrderDTO et converti les valeurs principal en Order pour la db.
     *
     * @param oDTO
     * @param order
     */
    private static void OrderDTO_To_Order(OrderDTO oDTO, Order order) {
        order.setTPS(oDTO.getTPS());
        order.setStateTax(oDTO.getStateTax());
        order.setTTC(oDTO.getTotalAmount());
        order.setStatus(oDTO.getStatus());
        order.setOrderDate(oDTO.getOrderDate());
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

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    @PostConstruct
    private void initNumber() {
        Optional<Order> lastOrderOptional = this.orderRepository.findTopByIdNumericPart();
        if (lastOrderOptional.isPresent()) {
            String lastId = lastOrderOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
            idNumber++;
            System.out.println("idNumber : " + idNumber);
        }
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