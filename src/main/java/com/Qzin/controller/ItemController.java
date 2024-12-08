package com.Qzin.controller;

import com.Qzin.Request.ItemRequestBody;
import com.Qzin.Request.ItemUpdateBody;
import com.Qzin.entity.Item;
import com.Qzin.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping("/menu/additem")
    public Item addItem(@RequestBody ItemRequestBody itemRequestBody) {
        return itemService.addItem(itemRequestBody);
    }

    @DeleteMapping("/delete/item/{itemUUID}")
    public void deleteItem(@PathVariable("itemUUID") String itemUUID) {
         itemService.deleteItem(itemUUID);
    }

    @PutMapping("/menu/updateitem")
    public Item updateItem(@RequestBody ItemUpdateBody itemUpdateBody) {
        return itemService.updateItem(itemUpdateBody);
    }
}
