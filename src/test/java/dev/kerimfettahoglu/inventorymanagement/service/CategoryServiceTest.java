package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.TestUtil;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void create() {
        Assertions.assertAll(() -> categoryService.create(TestUtil.dummyCategory()));
    }

    @Test
    void update_notFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            categoryService.update(TestUtil.dummyCategory());
        });
    }

    @Test
    void update() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyCategory()));
        Assertions.assertAll(() -> categoryService.update(TestUtil.dummyCategory()));
    }

    @Test
    void getAll_notFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> categoryService.getAll(1L));
    }

    @Test
    void getAll_withId() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyCategory()));
        Assertions.assertAll(() -> categoryService.getAll(1L));
    }

    @Test
    void getAll() {
        Mockito.when(categoryRepository.findAll())
                .thenReturn(List.of(TestUtil.dummyCategory()));
        Assertions.assertAll(() -> categoryService.getAll(null));
    }

    @Test
    void delete_notFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            categoryService.delete(1L);
        });
    }

    @Test
    void delete() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtil.dummyCategory()));
        Assertions.assertAll(() -> {
            categoryService.delete(1L);
        });
    }

}