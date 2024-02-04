package dev.kerimfettahoglu.inventorymanagement.api;

import dev.kerimfettahoglu.inventorymanagement.api.input.ProductNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.ProductUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductNewInput input) {
        return ResponseEntity.ok(productService.create(input));
    }

    @PutMapping
    public ResponseEntity<Product> update(@Valid @RequestBody ProductUpdateInput input) {
        return ResponseEntity.ok(productService.update(input));
    }

    @GetMapping
    public ResponseEntity<List<Product>> get(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(productService.getAll(id));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }

}
