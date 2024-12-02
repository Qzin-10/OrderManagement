package com.Qzin.service;

import com.Qzin.entity.Kitchen;
import com.Qzin.entity.Menu;

import java.util.List;

public interface KitchenService {

    Kitchen createKitchen(Kitchen kitchen);

    List<Menu> getMenuItemsByKitchen(int kitchenId);

    List<Menu> addMenuItems(int kitchenId, List<Menu> menuItems);
}
