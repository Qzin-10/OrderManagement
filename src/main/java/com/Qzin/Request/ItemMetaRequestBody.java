package com.Qzin.Request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemMetaRequestBody {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Image URL cannot be null")
    private String imageUrl;

    private String videoUrl;

    @NotNull(message = "VegStatus cannot be null")
    private boolean vegStatus;

    @NotNull(message = "Category cannot be null")
    private String category;

}

