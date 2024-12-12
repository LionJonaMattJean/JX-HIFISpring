package com.jxhifi.jxhifispring.repositories;
import com.jxhifi.jxhifispring.entities.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
//CHANGE THE UPDATE QUERY TO TAKE IN 2 PARAMETERS INSTEAD OF ONE

public interface CardRepository extends CrudRepository<Card, Integer> {

    //-FIND QUERRIES------------------------------------------------
    @Query("select c from Card c where c.id = : id")
    public Card getCardById(@Param("id") String id);

    @Query("select c from Card c where c.cardNumber=:cardNumber")
    public Card getCardByCardNumber(@Param("cardNumber") int cardNumber);

    //-MODIFYING QUERRIES-----------------------------------------------
    @Modifying
    @Query("update Card c set c.cardNumber =?2 where c.id =? 1")
    public void updateCardNumber(@Param("id")String id, @Param("newCardNumber") int newCardNumber);

    @Modifying
    @Query("update Card c set c.nameHolder = ?2 where c.id = ?1")
    public void updateName(@Param("id") String id, @Param("newName") String newName);

    @Modifying
    @Query("update  Card c set c.cvc =?2 where c.id = ?1")
    public void updateCvc(@Param("id")String id, @Param("newCvc") int newCvc);

    @Modifying
    @Query("update Card c set c.expiryDate =?2 where c.id =?1")
    public void updateExpDate(@Param("id") String id, @Param("newExpDate") LocalDate newExpDate);

    @Modifying
    @Query("update Card c set c.paymentMethod =?2 where c.id =?1")
    public void updatePayMethod(@Param("id")String id, @Param("newPayMethod") String newPayMethod);





}
