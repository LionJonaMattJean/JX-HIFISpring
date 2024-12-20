package com.jxhifi.jxhifispring.repositories;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.Optional;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "SELECT * FROM `card` ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Card> findTopByIdNumericPart();

    //-FIND QUERRIES------------------------------------------------
    @Query("select c from Card c where c.id = : id")
    Card getCardById(@Param("id") String id);

    @Query("select c from Card c where c.cardNumber=:cardNumber")
     Card getCardByCardNumber(@Param("cardNumber") int cardNumber);

    @Query("SELECT o.card FROM Order o WHERE o.id = :orderId")
    Card findCardByOrderId(@Param("orderId") String orderId);

    //-MODIFYING QUERRIES-----------------------------------------------
    @Transactional
    @Modifying
    @Query("update Card c set c.cardNumber =:newCardNumber where c.id =:id")
     void updateCardNumber(@Param("id")String id, @Param("newCardNumber") int newCardNumber);

    @Transactional
    @Modifying
    @Query("update Card c set c.nameHolder = :newName where c.id =:id")
     void updateName(@Param("id") String id, @Param("newName") String newName);

    @Transactional
    @Modifying
    @Query("update  Card c set c.cvc =:newCvc where c.id = :id")
     void updateCvc(@Param("id")String id, @Param("newCvc") int newCvc);

    @Transactional
    @Modifying
    @Query("update Card c set c.expiryDate =:newExpDate where c.id =:id")
     void updateExpDate(@Param("id") String id, @Param("newExpDate") LocalDate newExpDate);

    @Transactional
    @Modifying
    @Query("update Card c set c.paymentMethod =:newPayMethod where c.id =:id")
     void updatePayMethod(@Param("id")String id, @Param("newPayMethod") String newPayMethod);





}
