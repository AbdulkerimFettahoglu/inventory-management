package dev.kerimfettahoglu.inventorymanagement.api.input;

import lombok.Data;

@Data
public class ProductNewInput {

    private Long categoryId;
    private String name;
    private String brand;

}
