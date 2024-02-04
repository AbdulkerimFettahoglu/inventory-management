package dev.kerimfettahoglu.inventorymanagement.api.input;

import lombok.Data;

@Data
public class ExitNewInput {

    private Long productId;
    private Integer itemCount;
    private Double totalPrice;

}
