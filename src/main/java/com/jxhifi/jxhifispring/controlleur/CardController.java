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

    @GetMapping("/{TBD}/{orderId}")
    public ResponseEntity<Card> getCardByNumber(@PathVariable String orderId) {
        return ResponseEntity.ok(cardService.getCardByOrderId(orderId));
    }

    @PostMapping("TBD")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.addCard(card));
    }

    @PutMapping("/{customerId}/editCard")
    public ResponseEntity<Card> editCard(@PathVariable String customerId, @PathVariable String cardId, @RequestBody Card card) {
        Card newCard = cardService.getCardById(card.getId());
        newCard.setCardNumber( card.getCardNumber());
        newCard.setCvc(card.getCvc());
        newCard.setExpiryDate(card.getExpiryDate());
        newCard.setNameHolder(card.getNameHolder());
        newCard.setPaymentMethod(card.getPaymentMethod());
        cardService.updateCard(newCard);
        return ResponseEntity.ok(newCard);
    }

    @DeleteMapping("/card/delete/{id}")
    public void deleteCard(@PathVariable String id) {
        Card card = cardService.getCardById(id);
        if (card != null) {
            cardService.deleteCard(card);
        }
        else{
            throw new RuntimeException("Card not found");
        }
    }


}
