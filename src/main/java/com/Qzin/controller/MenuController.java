package com.Qzin.controller;

import com.Qzin.entity.Menu;
import com.Qzin.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public ResponseEntity<Menu> addMenuItem(@RequestParam int kitchenId,
                                            @RequestParam int inventoryItemId,
                                            @RequestParam BigDecimal price) {
        logger.info("Received request to add menu item with kitchenId: {}, inventoryItemId: {}, price: {}", kitchenId, inventoryItemId, price);
        try {
            Menu newMenuItem = menuService.addMenuItem(kitchenId, inventoryItemId, price);
            logger.info("Successfully added new menu item: {}", newMenuItem);
            return ResponseEntity.ok(newMenuItem);
        } catch (Exception e) {
            logger.error("Error occurred while adding menu item with kitchenId: {}, inventoryItemId: {}", kitchenId, inventoryItemId, e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<List<Menu>> getMenuForKitchen(@PathVariable int kitchenId) {
        logger.info("Received request to get menu for kitchenId: {}", kitchenId);
        try {
            List<Menu> menuItems = menuService.getMenuForKitchen(kitchenId);
            logger.info("Successfully fetched {} menu items for kitchenId: {}", menuItems.size(), kitchenId);
            return ResponseEntity.ok(menuItems);
        } catch (Exception e) {
            logger.error("Error occurred while fetching menu for kitchenId: {}", kitchenId, e);
            return ResponseEntity.status(500).build();
        }
    }
}




