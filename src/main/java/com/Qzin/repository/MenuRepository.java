package com.Qzin.repository;

import com.Qzin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findAllByKitchen_KitchenId(int kitchenId);;
}
