package com.Qzin.Request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateBody {
    boolean isServing;
    double price;
    String servingQuantity;
    @NotNull(message = "itemuuid cant be null")
    String itemUUID;
    int itemmetaId;
}
