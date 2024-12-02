package com.Qzin.controller;

import com.Qzin.entity.Kitchen;
import com.Qzin.entity.Menu;
import com.Qzin.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchens")
public class KitchenController {

  @Autowired
    KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @PostMapping("/add")
    public Kitchen createKitchen(@RequestBody Kitchen kitchen) {
        return kitchenService.createKitchen(kitchen);
    }

    @PostMapping("/{kitchenId}/addmenu")
    public List<Menu> addMenuItems(@PathVariable int kitchenId, @RequestBody List<Menu> menuItems) {
        return kitchenService.addMenuItems(kitchenId, menuItems);
    }

    @GetMapping("/{kitchenId}/menu")
    public List<Menu> getMenuItems(@PathVariable int kitchenId) {
        return kitchenService.getMenuItemsByKitchen(kitchenId);
    }
}

