package com.Qzin.service.impl;


import com.Qzin.entity.Inventory;
import com.Qzin.entity.Kitchen;
import com.Qzin.entity.Menu;
import com.Qzin.repository.InventoryRepository;
import com.Qzin.repository.KitchenRepository;
import com.Qzin.repository.MenuRepository;
import com.Qzin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Menu addMenuItem(int kitchenId, int inventoryItemId, BigDecimal price){
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(kitchenId);
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryItemId);

        if (kitchenOptional.isPresent() && inventoryOptional.isPresent()) {
            Kitchen kitchen = kitchenOptional.get();
            Inventory inventory = inventoryOptional.get();

            Menu menu = new Menu();
            menu.setKitchen(kitchen);
            menu.setInventory(inventory);
            menu.setPrice(price);
            return menuRepository.save(menu);
        }
        return null;
    }

    public List<Menu> getMenuForKitchen(int kitchenId) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(kitchenId);
        if (kitchenOptional.isPresent()) {
            return menuRepository.findByKitchen(kitchenOptional.get());
        }
        return new ArrayList<>();
    }
}

