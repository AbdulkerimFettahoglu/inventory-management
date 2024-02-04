package dev.kerimfettahoglu.inventorymanagement;

import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtil {

    public static Category dummyCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("name");
        return category;
    }
}
