package com.example.product.controller;

import com.example.product.model.Category;
import com.example.product.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // List all categories without pagination
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("No categories found");
        }
        return ResponseEntity.ok(categories);
    }
    //Search for a category by id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " not found"));
    }
    //Search for a category by name
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategory(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }

        List<Category> categories = categoryRepository.findByNameContaining(name);
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("No categories found with name containing: " + name);
        }

        return ResponseEntity.ok(categories);
    }

    // Paginated list of categories
    @GetMapping("/alll")
    public ResponseEntity<Page<Category>> getAllCategoryPaginated(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be non-negative and size must be greater than zero");
        }

        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page, size));
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("No categories found for the given page");
        }

        return ResponseEntity.ok(categories);
    }
}