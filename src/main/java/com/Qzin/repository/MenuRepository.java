package com.Qzin.repository;

import com.Qzin.entity.ItemMetaData;
import com.Qzin.entity.Kitchen;
import com.Qzin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m WHERE m.menuUUID=:menuUUID")
    Optional<Menu> getMenuByUUID(@Param("menuUUID") String menuUUID);

    List<Menu> findAllByKitchen_KitchenId(Long kitchenId);

}
