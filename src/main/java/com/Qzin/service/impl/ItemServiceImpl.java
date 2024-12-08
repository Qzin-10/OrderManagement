package com.Qzin.service.impl;

import com.Qzin.Request.ItemRequestBody;
import com.Qzin.controller.ItemController;
import com.Qzin.entity.Item;
import com.Qzin.entity.ItemMetaData;
import com.Qzin.entity.Menu;
import com.Qzin.repository.ItemMetaDataRepository;
import com.Qzin.repository.MenuRepository;
import com.Qzin.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemMetaDataRepository itemMetaDataRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Item addItem(ItemRequestBody itemRequestBody) {

        if(itemRequestBody != null) {
            Optional<ItemMetaData> itemMetaData = itemMetaDataRepository.getItemMetaDataByUUID(itemRequestBody.getItemMetaDataUUID());
            Optional<Menu> menu = menuRepository.getMenuByUUID(itemRequestBody.getMenuUUID());

            if(itemMetaData.isPresent() && menu.isPresent()) {
                Item item = Item.builder().servingQuantity(itemRequestBody.getServingQuantity())
                        .itemMetaId(itemMetaData.get().getItemMetaDataId())
                        .price(itemRequestBody.getPrice())
                        .menuUUID(itemRequestBody.getMenuUUID())
                        .build();

                return item;
            }
        } else {
            logger.error("No ItemMetaData found for given UUID {}", itemRequestBody.getItemMetaDataUUID());
        }
        return null;
    }
}
