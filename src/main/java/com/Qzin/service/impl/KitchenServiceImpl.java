package com.Qzin.service.impl;


import com.Qzin.entity.Kitchen;
import com.Qzin.entity.Menu;
import com.Qzin.repository.KitchenRepository;
import com.Qzin.repository.MenuRepository;
import com.Qzin.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitchenServiceImpl implements KitchenService {


    @Autowired
    KitchenRepository kitchenRepository;

    @Autowired
    MenuRepository menuRepository;

    public KitchenServiceImpl(KitchenRepository kitchenRepository, MenuRepository menuRepository) {
        this.kitchenRepository = kitchenRepository;
        this.menuRepository = menuRepository;
    }

    public Kitchen createKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public List<Menu> addMenuItems(int kitchenId, List<Menu> menuItems) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);
        if (kitchen.isPresent()) {
            menuItems.forEach(menu -> menu.setKitchen(kitchen.get()));
            return menuRepository.saveAll(menuItems);
        }
        throw new RuntimeException("Kitchen not found");
    }

    public List<Menu> getMenuItemsByKitchen(int kitchenId) {
        return menuRepository.findAllByKitchen_KitchenId(kitchenId);
    }
}
