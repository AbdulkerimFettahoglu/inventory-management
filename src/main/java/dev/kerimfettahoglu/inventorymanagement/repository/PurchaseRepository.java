package dev.kerimfettahoglu.inventorymanagement.repository;

import dev.kerimfettahoglu.inventorymanagement.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
