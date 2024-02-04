package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.TestUtil;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.CategoryRepository;
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
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void create_categoryNotFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                productService.create(TestUtil.dummyProductNewInput()));
    }

    @Test
    void create() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyCategory()));
        Assertions.assertAll(() -> productService.create(TestUtil.dummyProductNewInput()));
    }

    @Test
    void update_categoryNotFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            productService.update(TestUtil.dummyProductUpdateInput());
        });
    }

    @Test
    void update_productNotFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyCategory()));
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            productService.update(TestUtil.dummyProductUpdateInput());
        });
    }


    @Test
    void update() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyCategory()));
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> productService.update(TestUtil.dummyProductUpdateInput()));
    }

    @Test
    void getAll_notFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> productService.getAll(1L));
    }

    @Test
    void getAll_withId() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> productService.getAll(1L));
    }

    @Test
    void getAll() {
        Mockito.when(productRepository.findAll())
                .thenReturn(List.of(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> productService.getAll(null));
    }

    @Test
    void delete_notFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            productService.delete(1L);
        });
    }

    @Test
    void delete() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> {
            productService.delete(1L);
        });
    }

}