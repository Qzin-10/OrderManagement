package com.Qzin.service;

import com.Qzin.entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory addInventoryItem(Inventory inventory);

    List<Inventory> getAllInventoryItems();
}
