package dev.kerimfettahoglu.inventorymanagement;

import dev.kerimfettahoglu.inventorymanagement.api.input.*;
import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.entity.Exit;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.entity.Purchase;
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
        product.setId(1L);
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

    public static Purchase dummyPurchase() {
        Purchase input = new Purchase();
        input.setId(1L);
        input.setProduct(dummyProduct());
        input.setItemCost(2D);
        input.setItemCount(40);
        input.setTotalCost(80D);
        return input;
    }

    public static PurchaseNewInput dummyPurchaseNewInput() {
        PurchaseNewInput input = new PurchaseNewInput();
        input.setItemCost(2D);
        input.setItemCount(40);
        input.setProductId(1L);
        return input;
    }

    public static PurchaseUpdateInput dummyPurchaseUpdateInput() {
        PurchaseUpdateInput input = new PurchaseUpdateInput();
        input.setId(1L);
        input.setItemCost(2D);
        input.setItemCount(40);
        input.setProductId(1L);
        return input;
    }

    public static Exit dummyExit() {
        Exit input = new Exit();
        input.setId(1L);
        input.setProduct(dummyProduct());
        input.setItemCount(40);
        return input;
    }

    public static ExitNewInput dummyExitNewInput() {
        ExitNewInput input = new ExitNewInput();
        input.setItemCount(40);
        input.setProductId(1L);
        return input;
    }

    public static ExitUpdateInput dummyExitUpdateInput() {
        ExitUpdateInput input = new ExitUpdateInput();
        input.setId(1L);
        input.setItemCount(40);
        input.setProductId(1L);
        return input;
    }

}
