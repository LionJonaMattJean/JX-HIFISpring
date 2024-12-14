package com.jxhifi.jxhifispring.controlleur;


import com.jxhifi.jxhifispring.DTO.product.OrderItemDTO;
import com.jxhifi.jxhifispring.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{customerId}/items")
    public ResponseEntity<List<OrderItemDTO>> getShoppingCartItems(@PathVariable String customerId) {
        List<OrderItemDTO> items = shoppingCartService.getItemsByCustomerId(customerId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{customerId}/add")
    public ResponseEntity<Void> addItemToCart(@PathVariable String customerId, @RequestBody OrderItemDTO itemDTO) {
        shoppingCartService.addItemToCart(customerId, itemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{customerId}/remove")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable String customerId, @RequestParam Integer itemId) {
        shoppingCartService.removeItemFromCart(customerId, itemId);
        return ResponseEntity.ok().build();
    }
}
