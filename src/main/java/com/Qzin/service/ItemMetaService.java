package com.Qzin.service;

import com.Qzin.Request.ItemMetaDataUpdateRequestBody;
import com.Qzin.Request.ItemMetaRequestBody;
import com.Qzin.entity.ItemMetaData;

import java.util.List;

public interface ItemMetaService {
    public ItemMetaData addItemMeta(ItemMetaRequestBody itemMetaRequestBody);

    public List<ItemMetaData> getAllMetaData();

    void updateItemMedaData(ItemMetaDataUpdateRequestBody itemMetaDataUpdateRequestBody);
}
