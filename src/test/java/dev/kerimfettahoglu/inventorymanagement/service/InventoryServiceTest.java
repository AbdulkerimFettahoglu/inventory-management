package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.TestUtil;
import dev.kerimfettahoglu.inventorymanagement.api.output.InventorySummaryOutput;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void addItemsToInventory_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                inventoryService.addItemsToInventory(TestUtil.dummyPurchase()));
    }

    @Test
    void addItemsToInventory() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> {
            Product itemsAddedProduct = inventoryService.addItemsToInventory(TestUtil.dummyPurchase());
            Assertions.assertEquals(TestUtil.dummyProduct().getItemCount()
                    + TestUtil.dummyPurchase().getItemCount(), itemsAddedProduct.getItemCount());
        });
    }

    @Test
    void revertAddFromInventory_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                inventoryService.revertAddFromInventory(TestUtil.dummyPurchase()));
    }

    @Test
    void revertAddFromInventory() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> {
            Product itemsRevertedProduct = inventoryService.revertAddFromInventory(TestUtil.dummyPurchase());
            Assertions.assertEquals(TestUtil.dummyProduct().getItemCount()
                    - TestUtil.dummyPurchase().getItemCount(), itemsRevertedProduct.getItemCount());
        });
    }

    @Test
    void makeExitFromInventory_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                inventoryService.makeExitFromInventory(TestUtil.dummyExit()));
    }

    @Test
    void makeExitFromInventory() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> {
            Product itemsRevertedProduct = inventoryService.makeExitFromInventory(TestUtil.dummyExit());
            Assertions.assertEquals(TestUtil.dummyProduct().getItemCount()
                    - TestUtil.dummyPurchase().getItemCount(), itemsRevertedProduct.getItemCount());
        });
    }

    @Test
    void revertExitFromInventory_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                inventoryService.revertExitFromInventory(TestUtil.dummyExit()));
    }

    @Test
    void revertExitFromInventory() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> {
            Product itemsAddedProduct = inventoryService.revertExitFromInventory(TestUtil.dummyExit());
            Assertions.assertEquals(TestUtil.dummyProduct().getItemCount()
                    + TestUtil.dummyPurchase().getItemCount(), itemsAddedProduct.getItemCount());
        });
    }

    @Test
    void summary() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> {
            InventorySummaryOutput summary = inventoryService.summary();
            Assertions.assertEquals(1, summary.getProductDetails().size());
        });
    }
}