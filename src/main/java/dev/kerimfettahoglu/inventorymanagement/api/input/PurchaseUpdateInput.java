package dev.kerimfettahoglu.inventorymanagement.api.input;

import lombok.Data;

@Data
public class PurchaseUpdateInput {

    private Long id;
    private Long productId;
    private Integer itemCount;
    private Double itemCost;

}
