package dev.kerimfettahoglu.inventorymanagement.repository;

import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select sum(itemCount) from Product")
    Integer totalItemCount();

    @Query("select sum(totalCost) from Product")
    Double totalItemCost();

    @Query("select sum(medianCost) from Product")
    Double totalMedianCost();
}
