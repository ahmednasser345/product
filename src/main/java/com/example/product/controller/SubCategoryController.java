package com.example.product.controller;

import com.example.product.model.Subcategory;
import com.example.product.repository.SubcategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Subcategory>> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        if (subcategories.isEmpty()) {
            throw new EntityNotFoundException("No subcategories found");
        }
        return ResponseEntity.ok(subcategories);
    }
    //search for a subcategory by id
    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Long id) {
        return subcategoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory with ID " + id + " not found"));
    }
    //search for a subcategory by name
    @GetMapping("/search")
    public ResponseEntity<List<Subcategory>> searchSubcategories(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }

        List<Subcategory> subcategories = subcategoryRepository.findByNameContaining(name);
        if (subcategories.isEmpty()) {
            throw new EntityNotFoundException("No subcategories found with name containing: " + name);
        }

        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/alll")
    public ResponseEntity<Page<Subcategory>> getSubcategoriesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }

        Page<Subcategory> subcategories = subcategoryRepository.findAll(PageRequest.of(page, size));
        if (subcategories.isEmpty()) {
            throw new EntityNotFoundException("No subcategories found for the given page");
        }

        return ResponseEntity.ok(subcategories);
    }
}