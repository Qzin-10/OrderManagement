package com.Qzin.Request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemMetaDataUpdateRequestBody {

    @NotNull(message = "ItemMetaDataUUID should not be null")
    private String itemMetaDataUUID;

    private String name;

    private String description;

    private String imageUrl;

    private String videoUrl;

    private boolean vegStatus;

    private String category;

}
