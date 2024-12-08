package com.Qzin.controller;

import com.Qzin.Request.ItemMetaDataUpdateRequestBody;
import com.Qzin.Request.ItemMetaRequestBody;
import com.Qzin.Request.ItemRequestBody;
import com.Qzin.entity.Item;
import com.Qzin.entity.ItemMetaData;
import com.Qzin.service.ItemMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itemmeta")
public class ItemMetaController {

    @Autowired
    private ItemMetaService itemMetaService;

    @PostMapping("/additemmeta")
    public ItemMetaData addItem(@RequestBody ItemMetaRequestBody itemMetaRequestBody) {
        return itemMetaService.addItemMeta(itemMetaRequestBody);
    }

    @GetMapping("/allmetadata")
    public List<ItemMetaData> getAllMetaData() {
        return itemMetaService.getAllMetaData();
    }

    @PutMapping("/itemmetadatauuid/update")
    public void updateItemMedaData(@RequestBody ItemMetaDataUpdateRequestBody itemMetaDataUpdateRequestBody) {
        itemMetaService.updateItemMedaData(itemMetaDataUpdateRequestBody);
    }

}
