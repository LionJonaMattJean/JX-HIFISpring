package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{TBD/{orderId}")
    public ResponseEntity<Card> getCardByNumber(@PathVariable String orderId) {
        return ResponseEntity.ok(cardService.getCardByOrderId(orderId));
    }

    @PostMapping("TBD")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.addCard(card));
    }


}
