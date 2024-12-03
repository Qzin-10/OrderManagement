package com.Qzin.controller;


import com.Qzin.entity.Kitchen;
import com.Qzin.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/kitchens")
public class KitchenController {

    private static final Logger logger = LoggerFactory.getLogger(KitchenController.class);

    @Autowired
    private KitchenService kitchenService;

    @PostMapping("/add")
    public ResponseEntity<Kitchen> createKitchen(@RequestBody Kitchen kitchen) {
        logger.info("Received request to create kitchen: {}", kitchen);
        try {
            Kitchen newKitchen = kitchenService.createKitchen(kitchen);
            logger.info("Successfully created kitchen: {}", newKitchen);
            return ResponseEntity.ok(newKitchen);
        } catch (Exception e) {
            logger.error("Error occurred while creating kitchen: {}", kitchen, e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getAllKitchens")
    public ResponseEntity<List<Kitchen>> getAllKitchens() {
        logger.info("Received request to get all kitchens");
        try {
            List<Kitchen> kitchens = kitchenService.getAllKitchens();
            logger.info("Successfully fetched {} kitchens", kitchens.size());
            return ResponseEntity.ok(kitchens);
        } catch (Exception e) {
            logger.error("Error occurred while fetching kitchens", e);
            return ResponseEntity.status(500).build();
        }
    }
}


