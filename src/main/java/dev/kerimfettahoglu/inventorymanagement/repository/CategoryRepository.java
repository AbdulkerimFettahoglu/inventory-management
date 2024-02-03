package dev.kerimfettahoglu.inventorymanagement.repository;

import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
