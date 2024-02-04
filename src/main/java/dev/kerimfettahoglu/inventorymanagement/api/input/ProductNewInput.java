package dev.kerimfettahoglu.inventorymanagement.api.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductNewInput {

    @NotNull
    private Long categoryId;
    @NotNull
    private String name;
    @NotNull
    private String brand;

}
