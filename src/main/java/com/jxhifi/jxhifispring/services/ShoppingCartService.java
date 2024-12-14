package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.product.OrderItemDTO;
import com.jxhifi.jxhifispring.DTO.product.ShoppingCartItemDTO;
import com.jxhifi.jxhifispring.entities.*;
import com.jxhifi.jxhifispring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ShoppingCartService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<OrderItemDTO> getItemsByCustomerId(String customerId){

        List<Order> orders = orderRepository.getAllOrdersByCustomerId(customerId);

        List<OrderItem> orderItems  = orders.stream()
                                                    .flatMap(order ->order.getOrderItems().stream())
                                                    .toList();

        return orderItems.stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList());
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        return new OrderItemDTO(orderItem.getId(),
                                orderItem.getProduct().getName(),
                                orderItem.getQuantity());
    }

    public void addItemToCart(String customerId, OrderItemDTO itemDTO) {
        // Fetch order of customer
        Order order = orderRepository.getAllOrdersByCustomerId(customerId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active order found for customer"));

        // Créer un nouvel OrderItem et l'ajouter à la commande
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Créer un nouvel OrderItem et l'associer à l'Order
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productRepository.getReferenceById(orderItem.getProduct().getId()));
        orderItem.setQuantity(orderItem.getQuantity());
        orderItem.setOrder(order);

        orderItemRepository.save(orderItem);
    }

    public void removeItemFromCart(String customerId, Integer itemId) {
        // verify link between order and customer
        OrderItem orderItem = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!orderItem.getOrder().getIdCustomer().equals(customerId)) {
            throw new RuntimeException("Item does not belong to the customer");
        }

        orderItemRepository.delete(orderItem);
    }



/*
    @Autowired
    private CustomerRepositery customerRepository;
    @Autowired
    private AddressRepositery addressRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ProductRepository productRepository;

    // Add item to cart
    public ShoppingCart addItem(String customerId, ShoppingCartItemDTO itemDTO) {
        // search for item
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));

        // verify if item already exist
        OrderItem existingItem = orderItemRepository.findBycustomerIdAndProductId(customerId, product.getId());

        if (existingItem != null) {
            //if it exists, update item
            int newQuantity = existingItem.getQuantity() + itemDTO.getQuantity();
            existingItem.setQuantity(newQuantity);
            existingItem.setSubTotal(newQuantity * product.getSellPrice());
            orderItemRepository.save(existingItem);
        } else {
            //if not exist, add item
            OrderItem newItem = new OrderItem();
            newItem.setProduct(product);
            newItem.setQuantity(itemDTO.getQuantity());
            newItem.setSubTotal(itemDTO.getQuantity() * product.getSellPrice());
            orderItemRepository.save(newItem);
        }

        // return cart
        return getCart(customerId);
    }

    // delete item from cart
    public ShoppingCart removeItem(String customerId, String productId) {
        // delete item from db
        orderItemRepository.deleteBycustomerIdAndProductId(customerId, productId);

        // return cart
        return getCart(customerId);
    }

    // get whole cart
    public ShoppingCart getCart(String customerId) {
        List<OrderItem> items = orderItemRepository.findBycustomerId(customerId);
        return new ShoppingCart(customerId);
    }


    public Order checkout(ShoppingCart cart, String addressId, String cardId) {

        //Get for the customer
        Customer customer = customerRepository.findById(Long.valueOf(cart.getCustomerId()))
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

        //Get the Address
        Address address = addressRepository.findById(Long.valueOf(addressId))
                .orElseThrow(()->new IllegalArgumentException("Adresse introuvable"));

        //Get card info
        Card card = cardRepository.findById(Integer.valueOf(cardId))
                .orElseThrow(()->new IllegalArgumentException("Carte introuvable"));

        //creation of a new Order
        Order order = new Order();
        order.setId(UUID.randomUUID().toString()); // Génère un identifiant unique
        order.setCustomer(customer);
        order.setShippingAddress(address);
        order.setCard(card);
        order.setOrderDate(LocalDate.now());
        order.setStatus("Pending");

        //transfert item from the Shopping cart to as OrderItem
        List<OrderItem> orderItems = cart.getItems();
        order.setOrderItems(orderItems);

        //save order in the db
        Order savedOrder = orderRepository.save(order);

        //empty the cart once ready to pay
        cart.clear();

        return savedOrder;
    }

*/
}
