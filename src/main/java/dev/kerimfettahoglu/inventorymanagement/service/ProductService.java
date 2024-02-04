package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.api.input.ProductNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.ProductUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.CategoryRepository;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product create(ProductNewInput input) {
        Optional<Category> optionalCategory = categoryRepository.findById(input.getCategoryId());
        if (optionalCategory.isPresent()) {
            Product product = new Product();
            product.setName(input.getName());
            product.setBrand(input.getBrand());
            product.setCategory(optionalCategory.get());
            productRepository.save(product);
            return product;
        } else {
            throw new DataNotFoundException(input.getCategoryId());
        }
    }

    public Product update(ProductUpdateInput input) {
        Optional<Category> optionalCategory = categoryRepository.findById(input.getCategoryId());
        if (optionalCategory.isPresent()) {
            Optional<Product> optionalProduct = productRepository.findById(input.getId());
            if (optionalProduct.isPresent()) {
                Product updateProduct = optionalProduct.get();
                updateProduct.setCategory(optionalCategory.get());
                updateProduct.setBrand(input.getBrand());
                updateProduct.setName(input.getName());
                productRepository.save(updateProduct);
                return updateProduct;
            } else {
                throw new DataNotFoundException(input.getId());
            }
        } else {
            throw new DataNotFoundException(input.getCategoryId());
        }
    }

    public List<Product> getAll(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            return List.of(this.get(id));
        } else {
            return this.getAll();
        }
    }

    public Product get(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new DataNotFoundException(id);
        }
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Boolean delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        } else {
            throw new DataNotFoundException(id);
        }
    }

}
