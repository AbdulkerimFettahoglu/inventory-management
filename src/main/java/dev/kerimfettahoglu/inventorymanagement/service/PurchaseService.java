package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.api.input.PurchaseNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.PurchaseUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.entity.Purchase;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import dev.kerimfettahoglu.inventorymanagement.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final InventoryService inventoryService;

    @Transactional
    public Purchase create(PurchaseNewInput input) {
        Optional<Product> optionalProduct = productRepository.findById(input.getProductId());
        if (optionalProduct.isPresent()) {
            Purchase purchase = new Purchase();
            purchase.setItemCost(input.getItemCost());
            purchase.setItemCount(input.getItemCount());
            purchase.setProduct(optionalProduct.get());
            Double totalCost = input.getItemCost() * input.getItemCount();
            purchase.setTotalCost(totalCost);
            purchaseRepository.save(purchase);
            inventoryService.addItemsToInventory(purchase);
            return purchase;
        } else {
            throw new DataNotFoundException(input.getProductId());
        }
    }

    public Purchase update(PurchaseUpdateInput input) {
        Optional<Product> optionalProduct = productRepository.findById(input.getProductId());
        if (optionalProduct.isPresent()) {
            Optional<Purchase> optionalPurchase = purchaseRepository.findById(input.getId());
            if (optionalPurchase.isPresent()) {
                Purchase purchase = optionalPurchase.get();
                inventoryService.revertAddFromInventory(purchase);
                purchase.setItemCost(input.getItemCost());
                purchase.setItemCount(input.getItemCount());
                purchase.setProduct(optionalProduct.get());
                Double totalCost = input.getItemCost() * input.getItemCount();
                purchase.setTotalCost(totalCost);
                inventoryService.addItemsToInventory(purchase);
                purchaseRepository.save(purchase);
                return purchase;
            } else {
                throw new DataNotFoundException(input.getId());
            }
        } else {
            throw new DataNotFoundException(input.getProductId());
        }
    }

    public List<Purchase> getAll(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            return List.of(this.get(id));
        } else {
            return this.getAll();
        }
    }

    public Purchase get(Long id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            return optionalPurchase.get();
        } else {
            throw new DataNotFoundException(id);
        }
    }

    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    public Boolean delete(Long id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            inventoryService.revertAddFromInventory(optionalPurchase.get());
            purchaseRepository.delete(optionalPurchase.get());
            return true;
        } else {
            throw new DataNotFoundException(id);
        }
    }

}
