package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.product.ShoppingCartItemDTO;
import com.jxhifi.jxhifispring.entities.*;
import com.jxhifi.jxhifispring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class ShoppingCartService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepositery customerRepository;
    @Autowired
    private AddressRepositery addressRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    // Add item to cart
    public ShoppingCart addItem(String customerId, ShoppingCartItemDTO itemDTO) {
        // search for item
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));

        // verify if item already exist
        OrderItem existingItem = orderItemRepository.findByCustomerIdAndProductId(customerId, product.getId());

        if (existingItem != null) {
            //if exist, update item
            int newQuantity = existingItem.getQuantity() + itemDTO.getQuantity();
            existingItem.setQuantity(newQuantity);
            existingItem.setSubTotal(newQuantity * product.getSellPrice());
            orderItemRepository.save(existingItem);
        } else {
            //if not exist, add item
            OrderItem newItem = new OrderItem();
            newItem.setCustomerId(customerId);
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
        orderItemRepository.deleteByCustomerIdAndProductId(customerId, productId);

        // return cart
        return getCart(customerId);
    }

    // get whole cart
    public ShoppingCart getCart(String customerId) {
        List<OrderItem> items = orderItemRepository.findByCustomerId(customerId);
        return new ShoppingCart(customerId, items);
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
}
