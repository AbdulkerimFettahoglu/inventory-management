package dev.kerimfettahoglu.inventorymanagement.api.output;

import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import lombok.Data;

import java.util.Map;

@Data
public class InventorySummaryOutput {

    private Long productCount;
    private Integer totalItemCountInInventory;
    private Double totalCostOfItems;
    private Double totalMedianCost;
    private Map<Long, Product> productDetails;

}
