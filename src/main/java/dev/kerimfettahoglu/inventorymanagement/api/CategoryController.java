package dev.kerimfettahoglu.inventorymanagement.api;

import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> get(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(categoryService.getAll(id));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delet(@RequestParam Long id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }

}
