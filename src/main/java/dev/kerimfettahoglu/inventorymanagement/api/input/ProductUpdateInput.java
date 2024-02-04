package dev.kerimfettahoglu.inventorymanagement.api.input;

import lombok.Data;

@Data
public class ProductUpdateInput {

    private Long id;
    private Long categoryId;
    private String name;
    private String brand;

}
