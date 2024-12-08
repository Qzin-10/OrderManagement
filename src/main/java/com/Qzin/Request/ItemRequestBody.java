package com.Qzin.Request;

import lombok.Data;

@Data
public class ItemRequestBody {

    private Double price;
    private String servingQuantity;
    private String menuUUID;
    private String itemMetaDataUUID;

}
