package com.jxhifi.jxhifispring.entities;

import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    @Getter
    private String customerId;
    @Getter
    private List<OrderItem> items;

    /**Constructor with parameters
     *
     * @param customerId
     */
    public ShoppingCart(String customerId){
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    /**to add an item to the cart
     * sets the price according to the quantity
     * @param product
     * @param quantity
     */
    public void addItem(Product product, int quantity){
        OrderItem newItem = new OrderItem();
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        newItem.setSubTotal(quantity * product.getSellPrice());
        this.items.add(newItem);
    }

    /**to remove an item from the cart
     *
     * @param productId
     */
    public void removeItem(String productId){
        this.items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /**used to show the customer the total before the checkout     *
     *
     */
    public double calculateTotal(){
        return items.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }

    /** To empty the shopping cart
     *
     */
    public void clear(){
        this.items.clear();
    }
}
