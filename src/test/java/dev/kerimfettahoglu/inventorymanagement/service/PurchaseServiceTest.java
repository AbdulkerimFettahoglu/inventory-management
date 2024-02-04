package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.TestUtil;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import dev.kerimfettahoglu.inventorymanagement.repository.PurchaseRepository;
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
class PurchaseServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private InventoryService inventoryService;
    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    void create_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                purchaseService.create(TestUtil.dummyPurchaseNewInput()));
    }

    @Test
    void create() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> purchaseService.create(TestUtil.dummyPurchaseNewInput()));
    }

    @Test
    void update_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            purchaseService.update(TestUtil.dummyPurchaseUpdateInput());
        });
    }

    @Test
    void update_purchaseNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Mockito.when(purchaseRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            purchaseService.update(TestUtil.dummyPurchaseUpdateInput());
        });
    }

    @Test
    void update() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Mockito.when(purchaseRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyPurchase()));
        Assertions.assertAll(() -> purchaseService.update(TestUtil.dummyPurchaseUpdateInput()));
    }

    @Test
    void getAll_notFound() {
        Mockito.when(purchaseRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> purchaseService.getAll(1L));
    }

    @Test
    void getAll_withId() {
        Mockito.when(purchaseRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyPurchase()));
        Assertions.assertAll(() -> purchaseService.getAll(1L));
    }

    @Test
    void getAll() {
        Mockito.when(purchaseRepository.findAll())
                .thenReturn(List.of(TestUtil.dummyPurchase()));
        Assertions.assertAll(() -> purchaseService.getAll(null));
    }

    @Test
    void delete_notFound() {
        Mockito.when(purchaseRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            purchaseService.delete(1L);
        });
    }

    @Test
    void delete() {
        Mockito.when(purchaseRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyPurchase()));
        Assertions.assertAll(() -> {
            purchaseService.delete(1L);
        });
    }
}