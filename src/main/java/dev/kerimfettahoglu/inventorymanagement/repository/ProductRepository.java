package dev.kerimfettahoglu.inventorymanagement.repository;

import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
