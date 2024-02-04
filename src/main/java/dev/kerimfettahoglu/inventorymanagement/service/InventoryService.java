package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.api.output.InventorySummaryOutput;
import dev.kerimfettahoglu.inventorymanagement.entity.Exit;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.entity.Purchase;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    public Product addItemsToInventory(Purchase purchase) {
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

    public Product revertAddFromInventory(Purchase revertPurchase) {
        Optional<Product> optionalProduct = productRepository.findById(revertPurchase.getProduct().getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Integer newItemCount = product.getItemCount() - revertPurchase.getItemCount();
            Double newTotalCost = product.getTotalCost() - revertPurchase.getTotalCost();
            Double medianCost = newTotalCost / newItemCount.doubleValue();
            product.setItemCount(newItemCount);
            product.setTotalCost(newTotalCost);
            product.setMedianCost(medianCost);
            productRepository.save(product);
            return product;
        } else {
            throw new DataNotFoundException(revertPurchase.getProduct().getId());
        }
    }

    public Product makeExitFromInventory(Exit exit) {
        Optional<Product> optionalProduct = productRepository.findById(exit.getProduct().getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Integer newItemCount = product.getItemCount() - exit.getItemCount();
            Double newTotalCost = product.getTotalCost() - (product.getMedianCost() * exit.getItemCount());
            Double medianCost = newTotalCost / newItemCount.doubleValue();
            product.setItemCount(newItemCount);
            product.setTotalCost(newTotalCost);
            product.setMedianCost(medianCost);
            productRepository.save(product);
            return product;
        } else {
            throw new DataNotFoundException(exit.getProduct().getId());
        }
    }

    public Product revertExitFromInventory(Exit revertExit) {
        Optional<Product> optionalProduct = productRepository.findById(revertExit.getProduct().getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Integer totalItemCount = product.getItemCount() + revertExit.getItemCount();
            Double totalCost = product.getTotalCost() + (revertExit.getItemCount() * product.getMedianCost());
            Double medianCost = totalCost / totalItemCount.doubleValue();
            product.setItemCount(totalItemCount);
            product.setTotalCost(totalCost);
            product.setMedianCost(medianCost);
            productRepository.save(product);
            return product;
        } else {
            throw new DataNotFoundException(revertExit.getProduct().getId());
        }
    }

    public InventorySummaryOutput summary() {
        InventorySummaryOutput summary = new InventorySummaryOutput();
        Long productCount = productRepository.count();
        summary.setProductCount(productCount);
        summary.setTotalCostOfItems(productRepository.totalItemCost());
        summary.setTotalItemCountInInventory(productRepository.totalItemCount());
        summary.setTotalMedianCost(productRepository.totalMedianCost() / productCount);
        Map<Long, Product> productDetails = new HashMap<>();
        productRepository.findAll().stream()
                .forEach(product -> productDetails.put(product.getId(), product));
        summary.setProductDetails(productDetails);
        return summary;
    }

}
