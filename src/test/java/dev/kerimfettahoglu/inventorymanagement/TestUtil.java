package dev.kerimfettahoglu.inventorymanagement;

import dev.kerimfettahoglu.inventorymanagement.api.input.ProductNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.ProductUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtil {

    public static Category dummyCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        return category;
    }

    public static Product dummyProduct() {
        Product product = new Product();
        product.setCategory(dummyCategory());
        product.setName("name");
        product.setBrand("brand");
        product.setItemCount(40);
        product.setMedianCost(2D);
        product.setTotalCost(80D);
        return product;
    }

    public static ProductNewInput dummyProductNewInput() {
        ProductNewInput input = new ProductNewInput();
        input.setCategoryId(1L);
        input.setName("name");
        input.setBrand("brand");
        return input;
    }

    public static ProductUpdateInput dummyProductUpdateInput() {
        ProductUpdateInput input = new ProductUpdateInput();
        input.setId(1L);
        input.setCategoryId(1L);
        input.setName("name");
        input.setBrand("brand");
        return input;
    }

}
