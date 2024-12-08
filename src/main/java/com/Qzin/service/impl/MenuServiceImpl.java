package com.Qzin.service.impl;


import com.Qzin.entity.Kitchen;
import com.Qzin.entity.Menu;
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
    private MenuRepository menuRepository;

    public Menu addMenuItem(int kitchenId){
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(kitchenId);

        if (kitchenOptional.isPresent()) {
            Kitchen kitchen = kitchenOptional.get();
            Menu menu = new Menu();
            menu.setKitchen(kitchen);
            return menuRepository.save(menu);
        }
        return null;
    }

    public List<Menu> getMenuForKitchen(int kitchenId) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(kitchenId);
        if (kitchenOptional.isPresent()) {
            return menuRepository.findAllByKitchen_KitchenId(kitchenOptional.get().getKitchenId());
        }
        return new ArrayList<>();
    }
}

