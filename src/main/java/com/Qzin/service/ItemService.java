package com.Qzin.service;

import com.Qzin.Request.ItemRequestBody;
import com.Qzin.Request.ItemUpdateBody;
import com.Qzin.entity.Item;

public interface ItemService {

    public Item addItem(ItemRequestBody itemRequestBody);

    public void deleteItem( String itemUUID);

    public Item updateItem(ItemUpdateBody itemUpdateBody);

}
