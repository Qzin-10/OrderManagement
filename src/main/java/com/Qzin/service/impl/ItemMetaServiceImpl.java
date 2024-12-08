package com.Qzin.service.impl;

import com.Qzin.Request.ItemMetaDataUpdateRequestBody;
import com.Qzin.Request.ItemMetaRequestBody;
import com.Qzin.entity.ItemMetaData;
import com.Qzin.repository.ItemMetaDataRepository;
import com.Qzin.service.ItemMetaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                .itemMetaDataUUID(String.valueOf(UUID.randomUUID()))
                .build();

        itemMetaDataRepository.save(itemMetaData);
        return itemMetaData;
    }

    @Override
    public List<ItemMetaData> getAllMetaData() {
        return itemMetaDataRepository.findAll();
    }

    @Override
    public void updateItemMedaData(ItemMetaDataUpdateRequestBody itemMetaDataUpdateRequestBody) {

        Optional<ItemMetaData> itemMetaDataOptional = Optional.ofNullable(itemMetaDataRepository.getItemMetaDataByUUID(itemMetaDataUpdateRequestBody.getItemMetaDataUUID()).orElseThrow(
                () -> new EntityNotFoundException("ItemMetaData not found for the given UUID")
        ));

        if(itemMetaDataOptional.isPresent()) {

            ItemMetaData itemMetaData = itemMetaDataOptional.get();

            Optional.ofNullable(itemMetaDataUpdateRequestBody.getCategory()).ifPresent(itemMetaData :: setCategory);
            Optional.ofNullable(itemMetaDataUpdateRequestBody.getDescription()).ifPresent(itemMetaData :: setDescription);
            Optional.ofNullable(itemMetaDataUpdateRequestBody.getName()).ifPresent(itemMetaData :: setName);
            Optional.ofNullable(itemMetaDataUpdateRequestBody.getImageUrl()).ifPresent(itemMetaData :: setImageURL);
            Optional.ofNullable(itemMetaDataUpdateRequestBody.getVideoUrl()).ifPresent(itemMetaData :: setVideoURL);
            Optional.ofNullable(itemMetaDataUpdateRequestBody.isVegStatus()).ifPresent(itemMetaData :: setVegStatus);
            itemMetaData.setUpdatedAt(new Date());
            itemMetaDataRepository.save(itemMetaData);
        }
    }
}
