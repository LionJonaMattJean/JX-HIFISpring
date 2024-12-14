package com.jxhifi.jxhifispring.services;


import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.repositories.CardRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;

    private final EntityManager entityManager;

    public CardService(CardRepository cardRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.entityManager = entityManager;
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getCardById(String id){
        return cardRepository.getCardById(id);
    }

    public Card getCardByOrderId(String id) {
        return cardRepository.findCardByOrderId(id);
    }
    public Card getCardByNumber(int number){
        return cardRepository.getCardByCardNumber(number);
    }

    @Transactional
    public void updateCard(Card card){
        Card managedCard = entityManager.merge(card);
        cardRepository.save(managedCard);
    }

    /*public void updateCardNumber(String id, int newNumber) {
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
    }*/

    public void deleteCard(Card card){
        cardRepository.delete(card);
    }

    public void deleteCardbyId(String id){
        int converted = Integer.parseInt(id);
        cardRepository.deleteById(converted);
    }
    public void deleteCardByCardNumber(int cardNumber){
        cardRepository.deleteById(cardNumber);
    }

}
