package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.entity.Purchase;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    public Product addItemsToRepository(Purchase purchase) {
        Optional<Product> optionalProduct = productRepository.findById(purchase.getProduct().getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Integer totalItemCount = product.getItemCount() + purchase.getItemCount();
            Double totalCost = product.getTotalCost() + purchase.getTotalCost();
            Double medianCost = totalCost / totalItemCount.doubleValue();
            product.setItemCount(totalItemCount);
            product.setTotalCost(totalCost);
            product.setMedianCost(medianCost);
            productRepository.save(product);
            return product;
        } else {
            throw new DataNotFoundException(purchase.getProduct().getId());
        }
    }

}
