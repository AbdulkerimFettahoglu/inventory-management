package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category update(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isPresent()) {
            categoryRepository.save(category);
            return category;
        } else {
            throw new DataNotFoundException(category.getId());
        }
    }

    public List<Category> getAll(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            return List.of(this.get(id));
        } else {
            return this.getAll();
        }
    }

    public Category get(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new DataNotFoundException(id);
        }
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Boolean delete(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
            return true;
        } else {
            throw new DataNotFoundException(id);
        }
    }

}
