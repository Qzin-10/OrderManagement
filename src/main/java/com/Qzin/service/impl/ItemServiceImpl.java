package com.Qzin.service.impl;

import com.Qzin.Request.ItemRequestBody;
import com.Qzin.Request.ItemUpdateBody;
import com.Qzin.entity.Item;
import com.Qzin.entity.ItemMetaData;
import com.Qzin.entity.Menu;
import com.Qzin.repository.ItemMetaDataRepository;
import com.Qzin.repository.ItemRepository;
import com.Qzin.repository.MenuRepository;
import com.Qzin.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(ItemRequestBody itemRequestBody) {

        if(itemRequestBody != null) {
            Optional<ItemMetaData> itemMetaData = itemMetaDataRepository.getItemMetaDataByUUID(itemRequestBody.getItemMetaDataUUID());
            Optional<Menu> menu = menuRepository.getMenuByUUID(itemRequestBody.getMenuUUID());

            if(itemMetaData.isPresent() && menu.isPresent()) {
                Item item = Item.builder().servingQuantity(itemRequestBody.getServingQuantity())
                        .itemMeta(itemMetaData.get())
                        .price(itemRequestBody.getPrice())
                        .menu(menu.get())
                        .build();

                return item;
            }
        } else {
            logger.error("No ItemMetaData found for given UUID {}", itemRequestBody.getItemMetaDataUUID());
        }
        return null;
    }

    @Override
    public void deleteItem(String itemUUID) {
         itemRepository.deleteItemByItemUUID(itemUUID);
    }

    @Override
    public Item updateItem(ItemUpdateBody itemUpdateBody) {
        String itemUUID = itemUpdateBody.getItemUUID();

        if (itemUUID != null) {
            Optional<Item> itemOptional = itemRepository.getItemByUUID(itemUUID);

            if (itemOptional.isPresent()) {
                Item.ItemBuilder itemBuilder = Item.builder()
                        .servingQuantity(itemUpdateBody.getServingQuantity())
                        .price(itemUpdateBody.getPrice())
                        .isAvailable(itemUpdateBody.isAvailable());

                Optional<ItemMetaData> itemMetaData=itemMetaDataRepository.findById(itemUpdateBody.getItemmetaId());
                itemMetaData.ifPresent(itemBuilder::itemMeta);
                Item updatedItem = itemBuilder.build();
                itemRepository.save(updatedItem);
                return updatedItem;
            } else {
                throw new EntityNotFoundException("Item not found with UUID: " + itemUUID);
            }
        } else {
            throw new IllegalArgumentException("Item UUID cannot be null");
        }
    }

}
