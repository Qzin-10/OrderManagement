package com.Qzin.service;

import com.Qzin.entity.Menu;

import java.math.BigDecimal;
import java.util.List;

public interface MenuService {

    Menu addMenuItem(int kitchenId, int inventoryItemId, BigDecimal price);

    List<Menu> getMenuForKitchen(int kitchenId);
}