package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Store;
import org.apache.catalina.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends CrudRepository<Store, Integer> {
    //-FIND QUERRIES-----------------------------------------------------
    @Query("select s from Store s where s.id=: id")
    public Store findById(@Param("id") String id);

    //implementation optionel, au cas ou on voulait search by address
    @Query("select s from Store s where s.address=: addresse")
    public Store findByAddress(@Param("addresse") Address addresse);

    @Query("select'*' from Store")
    public List<Store> findAllStores();

    //-MODIFYING QUERRIES--------------------------------------------------
    @Modifying
    @Query("update Store s set s.address =?2 where s.id =?1")
    public void updateAddress(@Param("id")String id, @Param("newAddress") Address address);

    @Modifying
    @Query("update Store s set s.manager =?2 where s.id =?1")
    public void updateManager(@Param("id")String id, @Param("newManager") String manager);

    @Modifying
    @Query("update Store s set s.telephone =?2 where s.id =?1")
    public void updateTelephone(@Param("id")String id,@Param("newtelephone") String telephone);

    @Modifying
    @Query("update Store s set s.email =?2 where s.id =?1")
    public void updateEmail(@Param("id")String id ,@Param("newEmail") String email);


}
