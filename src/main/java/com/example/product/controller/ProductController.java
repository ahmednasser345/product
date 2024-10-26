package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // List all products without pagination
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found");
        }
        return ResponseEntity.ok(products);
    }
    //search for a product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));
    }
    //search for a product by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }

        List<Product> products = productRepository.findByNameContaining(name);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found with name containing: " + name);
        }

        return ResponseEntity.ok(products);
    }

    // Paginated list of products
    @GetMapping("/alll")
    public ResponseEntity<Page<Product>> getAllProductsPaginated(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be non-negative and size must be greater than zero");
        }

        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found for the given page");
        }

        return ResponseEntity.ok(products);
    }
}