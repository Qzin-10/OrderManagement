package com.Qzin.service;

import com.Qzin.Request.ItemMetaRequestBody;
import com.Qzin.entity.ItemMetaData;

public interface ItemMetaService {
    public ItemMetaData addItemMeta(ItemMetaRequestBody itemMetaRequestBody);
}
