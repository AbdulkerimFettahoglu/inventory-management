package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.TestUtil;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ExitRepository;
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
class ExitServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ExitRepository exitRepository;
    @Mock
    private InventoryService inventoryService;
    @InjectMocks
    private ExitService exitService;

    @Test
    void create_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () ->
                exitService.create(TestUtil.dummyExitNewInput()));
    }

    @Test
    void create() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Assertions.assertAll(() -> exitService.create(TestUtil.dummyExitNewInput()));
    }

    @Test
    void update_productNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            exitService.update(TestUtil.dummyExitUpdateInput());
        });
    }

    @Test
    void update_exitNotFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Mockito.when(exitRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            exitService.update(TestUtil.dummyExitUpdateInput());
        });
    }

    @Test
    void update() {
        Mockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyProduct()));
        Mockito.when(exitRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyExit()));
        Assertions.assertAll(() -> exitService.update(TestUtil.dummyExitUpdateInput()));
    }

    @Test
    void getAll_notFound() {
        Mockito.when(exitRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> exitService.getAll(1L));
    }

    @Test
    void getAll_withId() {
        Mockito.when(exitRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyExit()));
        Assertions.assertAll(() -> exitService.getAll(1L));
    }

    @Test
    void getAll() {
        Mockito.when(exitRepository.findAll())
                .thenReturn(List.of(TestUtil.dummyExit()));
        Assertions.assertAll(() -> exitService.getAll(null));
    }

    @Test
    void delete_notFound() {
        Mockito.when(exitRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            exitService.delete(1L);
        });
    }

    @Test
    void delete() {
        Mockito.when(exitRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyExit()));
        Assertions.assertAll(() -> {
            exitService.delete(1L);
        });
    }
}