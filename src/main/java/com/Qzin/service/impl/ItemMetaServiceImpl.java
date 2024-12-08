package com.Qzin.service.impl;

import com.Qzin.Request.ItemMetaRequestBody;
import com.Qzin.entity.ItemMetaData;
import com.Qzin.repository.ItemMetaDataRepository;
import com.Qzin.service.ItemMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMetaServiceImpl implements ItemMetaService {

    @Autowired
    ItemMetaDataRepository itemMetaDataRepository;

    @Override
    public ItemMetaData addItemMeta(ItemMetaRequestBody itemMetaRequestBody) {

        ItemMetaData itemMetaData=ItemMetaData.builder()
                .name(itemMetaRequestBody.getName())
                .vegStatus(itemMetaRequestBody.isVegStatus())
                .category(itemMetaRequestBody.getCategory())
                .imageURL(itemMetaRequestBody.getImageUrl())
                .videoURL(itemMetaRequestBody.getVideoUrl())
                .description(itemMetaRequestBody.getDescription())
                .build();

        itemMetaDataRepository.save(itemMetaData);
        return itemMetaData;
    }
}
