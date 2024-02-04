package dev.kerimfettahoglu.inventorymanagement.api.input;

import lombok.Data;

@Data
public class PurchaseNewInput {

    private Long productId;
    private Integer itemCount;
    private Double itemCost;

}
