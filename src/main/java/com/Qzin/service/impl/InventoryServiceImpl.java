package com.Qzin.service.impl;

import com.Qzin.entity.Inventory;
import com.Qzin.repository.InventoryRepository;
import com.Qzin.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory addInventoryItem(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }
}

