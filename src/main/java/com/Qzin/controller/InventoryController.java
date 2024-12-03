package com.Qzin.controller;

import com.Qzin.entity.Inventory;
import com.Qzin.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    // Add an inventory item
    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventoryItem(@RequestBody Inventory inventoryItem) {
        logger.info("Received request to add inventory item: {}", inventoryItem);
        try {
            Inventory newItem = inventoryService.addInventoryItem(inventoryItem);
            logger.info("Successfully added inventory item: {}", newItem);
            return ResponseEntity.ok(newItem);
        } catch (Exception e) {
            logger.error("Error occurred while adding inventory item: {}", inventoryItem, e);
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/getAllItems")
    public ResponseEntity<List<Inventory>> getAllInventoryItems() {
        logger.info("Received request to get all inventory items");
        try {
            List<Inventory> inventoryItems = inventoryService.getAllInventoryItems();
            logger.info("Successfully fetched {} inventory items", inventoryItems.size());
            return ResponseEntity.ok(inventoryItems);
        } catch (Exception e) {
            logger.error("Error occurred while fetching inventory items", e);
            return ResponseEntity.status(500).build();
        }
    }
}


