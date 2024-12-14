package com.jxhifi.jxhifispring.controlleur;


import com.jxhifi.jxhifispring.DTO.product.CheckoutDTO;
import com.jxhifi.jxhifispring.DTO.product.ShoppingCartItemDTO;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.ShoppingCart;
import com.jxhifi.jxhifispring.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/{customerId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable String customerId) {
        ShoppingCart cart = shoppingCartService.getCart(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}/items")
    public ResponseEntity<ShoppingCart> addItem(
            @PathVariable String customerId,
            @RequestBody ShoppingCartItemDTO itemDTO) {
        ShoppingCart cart = shoppingCartService.addItem(customerId, itemDTO);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{customerId}/items/{productId}")
    public ResponseEntity<ShoppingCart> removeItem(
            @PathVariable String customerId, @PathVariable String productId) {
        ShoppingCart cart = shoppingCartService.removeItem(customerId, productId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}/checkout")
    public ResponseEntity<Order> checkout(
            @PathVariable String customerId,
            @RequestBody CheckoutDTO checkoutDTO) {

        ShoppingCart cart = shoppingCartService.getCart(customerId);
        Order order = shoppingCartService.checkout(cart, checkoutDTO.getAddressId(),checkoutDTO.getCardId());
        return ResponseEntity.ok(order);
    }


}
