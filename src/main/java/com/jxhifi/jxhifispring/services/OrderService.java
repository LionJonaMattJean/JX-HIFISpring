package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.ConvertAddress_To_AddressDTO;
import com.jxhifi.jxhifispring.DTO.Order.DashboardDetail_OrderDTO;
import com.jxhifi.jxhifispring.DTO.Order.DashboardVersion_OrderDTO;
import com.jxhifi.jxhifispring.DTO.Order.DashboardDetail_OrderItemDTO;
import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.OrderItem;
import com.jxhifi.jxhifispring.repositories.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService extends ConvertAddress_To_AddressDTO {

    private static long idNumber = 1L;
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;


    public List<DashboardVersion_OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::orderToDashboardDTO)
                .collect(Collectors.toList());
    }

    public DashboardDetail_OrderDTO getOrderByIdForDashboardDetail(String id) {
        Order order = orderRepository.findOrderById(id);
        return orderToDashboardDTO_Detail(order);
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

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(String id) {
        return orderRepository.findOrderById(id);
    }

    public String getIdCustomerByOrderId(String id) {
        return orderRepository.findIdCustomerByOrder(id);
    }

    public Card getCardByOrderId(String id) {
        return orderRepository.findCardByOrderId(id);
    }

    public List<OrderItem> getOrderItemsByOrderId(String id) {
        return orderRepository.getOrderItemsByOrderId(id);
    }

    public List<Order> getAllOrdersFromCustomerId(String id) {
        return orderRepository.getAllOrdersByCustomerId(id);
    }

    public void updateShippingAddress(String id, Address address) {
        orderRepository.updateShippingAddress(id, address);
    }

    public void updateOrderStatus(String id, String newStatus) {
        orderRepository.updateOrderStatus(id, newStatus);
    }

    public void updateOrder(Order order) {
        Order managedOrder = entityManager.merge(order);
        orderRepository.save(managedOrder);
    }

    public void deleteOrder(String id) {
        int converted = Integer.parseInt(id);
        orderRepository.deleteById(converted);
    }

    public synchronized String generateNewId() {
        String id = "ORD" + idNumber;
        idNumber++;
        return id;
    }

    public DashboardVersion_OrderDTO orderToDashboardDTO(Order order) {
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

    public DashboardDetail_OrderDTO orderToDashboardDTO_Detail(Order order) {
        DashboardDetail_OrderDTO dto = new DashboardDetail_OrderDTO();

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTTC());

        dto.setOrderItems(order.getOrderItems().stream().map(item -> {
            DashboardDetail_OrderItemDTO dshbdoiDTO = new DashboardDetail_OrderItemDTO();
            dshbdoiDTO.setProductName(item.getProduct().getName());
            dshbdoiDTO.setQuantity(item.getQuantity());
            dshbdoiDTO.setSousTotal(item.getSubTotal());
            return dshbdoiDTO;
        }).collect(Collectors.toList()));

        dto.setCustomerFirstName(order.getCustomer().getFirstName());
        dto.setCustomerLastName(order.getCustomer().getLastName());
        dto.setCustomerEmail(order.getCustomer().getEmail());
        dto.setCustomerPhone(order.getCustomer().getPhone());

        dto.setShippingAddress(addressToAddressDTO(order.getShippingAddress()));

        return dto;
    }
}
