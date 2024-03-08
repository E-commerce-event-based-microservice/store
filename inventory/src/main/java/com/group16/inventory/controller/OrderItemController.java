package com.group16.inventory.controller;

import com.group16.inventory.domain.po.OrderItem;
import com.group16.inventory.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

// REST controller for handling order-item-related requests
@Tag(name = "Order Item Management Interfaces") // Swagger tag for order item management
@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    private final IOrderItemService orderItemService;

    // Constructor-based DI for service layer
    @Autowired
    public OrderItemController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Endpoint for creating a new order item
    @Operation(summary = "Create a new order item")
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        orderItemService.save(orderItem);
        return ResponseEntity.ok(orderItem);
    }

    // Endpoint for retrieving an order item by ID
    @Operation(summary = "Retrieve an order item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@Parameter(description = "Order Item ID") @PathVariable Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        return orderItem != null ? ResponseEntity.ok(orderItem) : ResponseEntity.notFound().build();
    }

    // Endpoint for retrieving all order items
    @Operation(summary = "Retrieve all order items")
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.list();
        return ResponseEntity.ok(orderItems);
    }

    // Endpoint for updating an existing order item
    @Operation(summary = "Update an existing order item by ID")
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@Parameter(description = "Order Item ID") @PathVariable Long id,
                                                     @RequestBody OrderItem orderItemDetails) {
        orderItemDetails.setOrderItemId(id);
        boolean updated = orderItemService.updateById(orderItemDetails);
        return updated ? ResponseEntity.ok(orderItemDetails) : ResponseEntity.notFound().build();
    }

    // Endpoint for deleting an order item by ID
    @Operation(summary = "Delete an order item by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@Parameter(description = "Order Item ID") @PathVariable Long id) {
        boolean removed = orderItemService.removeById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
