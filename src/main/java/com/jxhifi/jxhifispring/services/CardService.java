package com.jxhifi.jxhifispring.services;


import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.repositories.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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


    public void updateCardNumber(String id, int newNumber) {
        cardRepository.updateCardNumber(id, newNumber);
    }
    public void updateCardExDate(String id, LocalDate newDate) {
        cardRepository.updateExpDate(id, newDate);
    }
    public void updateCardCvc(String id, int newCvc) {
        cardRepository.updateCvc(id, newCvc);
    }
    public void updateCardNameHolder(String id, String nameHolder) {
        cardRepository.updateName(id, nameHolder);
    }
    public void updatePaymentType(String id, String paymentType){
        cardRepository.updatePayMethod(id, paymentType);
    }



    public void deleteCardbyId(String id){
        int converted = Integer.parseInt(id);
        cardRepository.deleteById(converted);
    }
    public void deleteCardByCardNumber(int cardNumber){
        cardRepository.deleteById(cardNumber);
    }

}
