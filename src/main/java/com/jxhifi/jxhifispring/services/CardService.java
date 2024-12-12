package com.jxhifi.jxhifispring.services;


import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.repositories.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getCardById(String id) {
        return cardRepository.getCardById(id);
    }
    public Card getCardByNumber(int number){
        return cardRepository.getCardByCardNumber(number);
    }

    public void updateCard(Card card) {
        cardRepository.updateCardNumber( card.getCardNumber());
        cardRepository.updateName(card.getNameHolder());
        cardRepository.updateCvc(card.getCvc());
        cardRepository.updateExpDate(card.getExpiryDate());
        cardRepository.updatePayMethod(card.getPaymentMethod());
    }


    public void deleteCardbyId(String id){
        int converted = Integer.parseInt(id);
        cardRepository.deleteById(converted);
    }
    public void deleteCardByCardNumber(int cardNumber){
        cardRepository.deleteById(cardNumber);
    }

}
