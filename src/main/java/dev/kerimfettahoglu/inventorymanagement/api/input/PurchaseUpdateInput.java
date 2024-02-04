package dev.kerimfettahoglu.inventorymanagement.api.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseUpdateInput {

    @NotNull
    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    @Min(value = 1, message = "item count should be bigger than 0.")
    private Integer itemCount;
    @NotNull
    private Double itemCost;

}
