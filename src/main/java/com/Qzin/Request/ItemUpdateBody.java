package com.Qzin.Request;

import lombok.Data;

@Data
public class ItemUpdateBody {
    boolean isAvailable;
    double price;
    String servingQuantity;
    String itemUUID;
    int itemmetaId;
}
