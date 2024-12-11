package com.jxhifi.jxhifispring.repositories;
import com.jxhifi.jxhifispring.entities.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CardRepository extends CrudRepository<Card, Integer> {

    //-FIND QUERRIES------------------------------------------------
    @Query("select c from Card c where c.id = : id")
    public Card getCardById(@Param("id") String id);

    @Query("select c from Card c where c.cardNumber=:cardNumber")
    public Card getCardByCardNumber(@Param("cardNumber") int cardNumber);

    //-MODIFYING QUERRIES-----------------------------------------------
    @Modifying
    @Query("update Card c set c.cardNumber =: newCardNumber")
    public void updateCardNumber(@Param("newCardNumber") int newCardNumber);

    @Modifying
    @Query("update Card c set c.nameHolder =: newName")
    public void updateName(@Param("newName") String newName);

    @Modifying
    @Query("update  Card c set c.cvc =: newCvc")
    public void updateCvc(@Param("newCvc") int newCvc);

    @Modifying
    @Query("update Card c set c.expiryDate =: newExpDate")
    public void updateExpDate(@Param("newExpDate") LocalDate newExpDate);

    @Modifying
    @Query("update Card c set c.paymentMethod =: newPayMethod")
    public void updatePayMethod(@Param("newPayMethod") String newPayMethod);





}
