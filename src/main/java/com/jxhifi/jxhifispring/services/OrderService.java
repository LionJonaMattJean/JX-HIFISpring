package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.ConvertAddress_To_AddressDTO;
import com.jxhifi.jxhifispring.DTO.Order.*;
import com.jxhifi.jxhifispring.entities.*;
import com.jxhifi.jxhifispring.repositories.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService extends ConvertAddress_To_AddressDTO {
    @Autowired
    private CustomerService customerService;
    private static long idNumber = 1L;
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;


    public List<DashboardVersion_OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::orderToDashboardDTO)
                .collect(Collectors.toList());
    }

    //TODO a supprimer si la version OrderDTO uniquement et suffisante.
//    public DashboardDetail_OrderDTO getOrderByIdForDashboardDetail(String id) {
//        Order order = orderRepository.findOrderById(id);
//        return orderToDashboardDTO_Detail(order);
//    }

    public OrderDTO getOrderById(String id) {
        Order order = orderRepository.findOrderById(id);
        return orderToDTO(order);
    }

    public Order getExistingOrder(String id) {
        Order order = orderRepository.findOrderById(id);
        return order;
    }


    //*********************************************** Methode de converstion en DTO ou inversement ***************************************************

    private DashboardVersion_OrderDTO orderToDashboardDTO(Order order) {
        DashboardVersion_OrderDTO dto = new DashboardVersion_OrderDTO();

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotal(order.getTTC());

        //passe au traver chaque OrderItem pour fetch les qty total de chaque produit commander
        dto.setNbrProducts(order.getOrderItems().stream()
                .mapToInt(OrderItem::getQuantity).sum());

        dto.setCustomerId(order.getCustomer().getId());
        dto.setCustomerMail(order.getCustomer().getEmail());

        return dto;
    }
//    private DashboardDetail_OrderDTO orderToDashboardDTO_Detail(Order order) {
//        DashboardDetail_OrderDTO dto = new DashboardDetail_OrderDTO();
//        dto.setId(order.getId());
//        dto.setOrderDate(order.getOrderDate());
//        dto.setStatus(order.getStatus());
//        dto.setTotalAmount(order.getTTC());
//        dto.setOrderItems(order.getOrderItems()
//                .stream().map(this::convertAllOderItem_ToDTO).collect(Collectors.toList()));
//        dto.setCustomerFirstName(order.getCustomer().getFirstName());
//        dto.setCustomerLastName(order.getCustomer().getLastName());
//        dto.setCustomerEmail(order.getCustomer().getEmail());
//        dto.setCustomerPhone(order.getCustomer().getPhone());
//        dto.setShippingAddress(addressToAddressDTO(order.getShippingAddress()));
//        return dto;
//    }
    private OrderDTO orderToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setTPS(order.getTPS());
        dto.setStateTax(order.getStateTax());
        dto.setTotalAmount(order.getTTC());
        dto.setStatus(order.getStatus());
        dto.setOrderItems(order.getOrderItems()
                .stream().map(this::convertAllOderItem_ToDTO).collect(Collectors.toList()));
        dto.setCard(convertCardToCardDTO(order.getCard()));
        dto.setShippingAddress(addressToAddressDTO(order.getShippingAddress()));
        dto.setOrderDate(order.getOrderDate());
        dto.setCustomer(customerService.ToDTO(order.getCustomer()));
        return dto;
    }


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
     * converti chaque item d'une liste OrderItem pour sa version transferable
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



    /*
        @PostConstruct
        private void initNumber(){
            Optional<Order> lastOrderOptional = this.orderRepository.findTopByIdNumericPart();
            if(lastOrderOptional.isPresent()){
                String lastId = lastOrderOptional.get().getId();
                idNumber = Long.parseLong(lastId.substring(3));
            }
        }
    */

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

//    public void addOrder(Order order) {
//        orderRepository.save(order);
//    }
//
//    public Order getOrderById(String id) {
//        return orderRepository.findOrderById(id);
//    }
//
//    public String getIdCustomerByOrderId(String id) {
//        return orderRepository.findIdCustomerByOrder(id);
//    }
//
//    public Card getCardByOrderId(String id) {
//        return orderRepository.findCardByOrderId(id);
//    }
//
//    public List<OrderItem> getOrderItemsByOrderId(String id) {
//        return orderRepository.getOrderItemsByOrderId(id);
//    }

    public List<Order> getAllOrdersFromCustomerId(String id) {
        return orderRepository.getAllOrdersByCustomerId(id);
    }

//    public void updateShippingAddress(String id, Address address) {
//        orderRepository.updateShippingAddress(id, address);
//    }
//
//    public void updateOrderStatus(String id, String newStatus) {
//        orderRepository.updateOrderStatus(id, newStatus);
//    }
//
//    public void updateOrder(Order order) {
//        Order managedOrder = entityManager.merge(order);
//        orderRepository.save(managedOrder);
//    }
//
//    public void deleteOrder(String id) {
//        int converted = Integer.parseInt(id);
//        orderRepository.deleteById(converted);
//    }

    public synchronized String generateNewId() {
        String id = "ORD" + idNumber;
        idNumber++;
        return id;
    }


}
