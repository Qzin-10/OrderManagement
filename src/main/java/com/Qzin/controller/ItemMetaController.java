package com.Qzin.controller;

import com.Qzin.Request.ItemMetaRequestBody;
import com.Qzin.Request.ItemRequestBody;
import com.Qzin.entity.Item;
import com.Qzin.entity.ItemMetaData;
import com.Qzin.service.ItemMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemmeta")
public class ItemMetaController {

    @Autowired
    private ItemMetaService itemMetaService;

    @PostMapping("/additemmeta")
    public ItemMetaData addItem(@RequestBody ItemMetaRequestBody itemMetaRequestBody) {
        return itemMetaService.addItemMeta(itemMetaRequestBody);
    }

}
