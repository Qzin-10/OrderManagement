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
import jakarta.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .isAvailable(true)
                        .itemUUID(String.valueOf(UUID.randomUUID()))
                        .build();

                itemRepository.save(item);

                return item;
            }
        } else {
            logger.error("No ItemMetaData found for given UUID {}", itemRequestBody.getItemMetaDataUUID());
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteItem(String itemUUID) {
         itemRepository.deleteByItemUUID(itemUUID);
    }

    @Override
    public Item updateItem(ItemUpdateBody itemUpdateBody) {
        String itemUUID = itemUpdateBody.getItemUUID();

        if (itemUUID == null) {
            throw new IllegalArgumentException("Item UUID cannot be null");
        }

        Item item = itemRepository.getItemByUUID(itemUUID)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with UUID: " + itemUUID));

        Optional.ofNullable(itemUpdateBody.getServingQuantity())
                .ifPresent(item::setServingQuantity);

        Optional.ofNullable(itemUpdateBody.getPrice())
                .filter(price -> price != 0)
                .ifPresent(item::setPrice);

        Optional.ofNullable(itemUpdateBody.isServing())
                .ifPresent(item::setAvailable);

        if (itemUpdateBody.getItemmetaId() > 0) {
            itemMetaDataRepository.findById(itemUpdateBody.getItemmetaId())
                    .ifPresent(item::setItemMeta);
        }

        itemRepository.save(item);

        return item;
    }



}
