package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Store;
import org.apache.catalina.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends CrudRepository<Store, Integer> {
    //-FIND QUERRIES-----------------------------------------------------
    @Query("select s from Store s where s.id=: id")
    public Store findById(@Param("id") String id);

    @Query("select s from Store s where s.address=: addresse")
    public Store findByAddress(@Param("addresse") Address addresse);

    //-MODIFYING QUERRIES--------------------------------------------------
    @Modifying
    @Query("update Store s set s.address =: newAddress")
    public void updateAddress(@Param("newAddress") Address address);

    @Modifying
    @Query("update Store s set s.manager =: newManager")
    public void updateManager(@Param("newManager") String manager);

    @Modifying
    @Query("update Store s set s.telephone =: newtelephone")
    public void updateTelephone(@Param("newtelephone") String telephone);

    @Modifying
    @Query("update Store s set s.email =: newEmail")
    public void updateEmail(@Param("newEmail") String email);


}
