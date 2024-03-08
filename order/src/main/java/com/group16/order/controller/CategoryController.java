package com.group16.order.controller;

import com.group16.order.domain.po.Category;
import com.group16.order.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

// REST controller for handling category-related requests
@Tag(name = "Category Management Interfaces") // Updated
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    // Constructor-based DI for service layer
    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Endpoint for creating a new category
    @Operation(summary = "Add a new category")
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    // Endpoint for retrieving a category by ID
    @Operation(summary = "Query category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@Parameter(description = "Category ID") @PathVariable Long id) {
        Category category = categoryService.getById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    // Endpoint for retrieving all categories
    @Operation(summary = "Query all categories")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.list();
        return ResponseEntity.ok(categories);
    }

    // Endpoint for updating an existing category
    @Operation(summary = "Update category by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@Parameter(description = "Category ID") @PathVariable Long id,
                                                   @RequestBody Category categoryDetails) {
        categoryDetails.setCategoryId(id);
        boolean updated = categoryService.updateById(categoryDetails);
        return updated ? ResponseEntity.ok(categoryDetails) : ResponseEntity.notFound().build();
    }

    // Endpoint for deleting a category by ID
    @Operation(summary = "Delete category by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@Parameter(description = "Category ID") @PathVariable Long id) {
        boolean removed = categoryService.removeById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
