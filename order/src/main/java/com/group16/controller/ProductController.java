package com.group16.controller;

import com.group16.domain.po.Product;
import com.group16.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST controller for handling product-related requests
@Tag(name = "Product Management Interfaces") // Swagger tag for product management
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    // Endpoint for creating a new product
    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    // Endpoint for querying products by ids
    @Operation(summary = "Query products by batch of IDs") // Updated
    @GetMapping("/byIds")
    public List<Product> queryProductByIds(@RequestParam("ids") List<Long> ids) {
        return productService.queryProductByIds(ids);
    }

    // Endpoint for retrieving a product by ID
    @Operation(summary = "Retrieve a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@Parameter(description = "Product ID") @PathVariable Long id) {
        Product product = productService.getById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Endpoint for retrieving all products
    @Operation(summary = "Retrieve all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.list();
        return ResponseEntity.ok(products);
    }

    // Endpoint for updating an existing product
    @Operation(summary = "Update an existing product by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@Parameter(description = "Product ID") @PathVariable Long id, @RequestBody Product productDetails) {
        productDetails.setProductId(id);
        boolean updated = productService.updateById(productDetails);
        return updated ? ResponseEntity.ok(productDetails) : ResponseEntity.notFound().build();
    }

    // Endpoint for deleting a product by ID
    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "Product ID") @PathVariable Long id) {
        boolean removed = productService.removeById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
