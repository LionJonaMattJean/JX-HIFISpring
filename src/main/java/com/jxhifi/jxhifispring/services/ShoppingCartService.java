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

    // Ajouter un article au panier
    public ShoppingCart addItem(String customerId, ShoppingCartItemDTO itemDTO) {
        // Récupérer le produit
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));

        // Vérifier si un item avec ce produit existe déjà pour ce client
        OrderItem existingItem = orderItemRepository.findByCustomerIdAndProductId(customerId, product.getId());

        if (existingItem != null) {
            // Si l'article existe, mettez à jour la quantité
            int newQuantity = existingItem.getQuantity() + itemDTO.getQuantity();
            existingItem.setQuantity(newQuantity);
            existingItem.setSubTotal(newQuantity * product.getSellPrice());
            orderItemRepository.save(existingItem);
        } else {
            // Si l'article n'existe pas, ajoutez-en un nouveau
            OrderItem newItem = new OrderItem();
            newItem.setCustomerId(customerId);
            newItem.setProduct(product);
            newItem.setQuantity(itemDTO.getQuantity());
            newItem.setSubTotal(itemDTO.getQuantity() * product.getSellPrice());
            orderItemRepository.save(newItem);
        }

        // Reconstruire le panier
        return getCart(customerId);
    }

    // Supprimer un article du panier
    public ShoppingCart removeItem(String customerId, String productId) {
        // Supprime l'article de la base de données
        orderItemRepository.deleteByCustomerIdAndProductId(customerId, productId);

        // Reconstruire le panier
        return getCart(customerId);
    }

    // Obtenir le panier complet pour un client
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
