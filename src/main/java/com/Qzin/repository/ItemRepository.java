package com.Qzin.repository;

import com.Qzin.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i FROM Item i WHERE i.itemUUID=:itemUUID")
    Optional<Item> getItemByUUID(@Param("itemUUID") String itemUUID);

    void deleteByItemUUID(String itemUUID);
}
