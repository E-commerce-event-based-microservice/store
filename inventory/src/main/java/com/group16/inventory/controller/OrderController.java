package com.group16.inventory.controller;

import com.group16.inventory.domain.po.Order;
import com.group16.inventory.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

// REST controller for handling order-related requests
@Tag(name = "Order Management Interfaces") // Swagger tag for order management
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @Operation(summary = "Create a new order")
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        orderService.save(order);
        return ResponseEntity.ok(order); // Return the created order
    }

    // Retrieve an order by ID
    @Operation(summary = "Retrieve an order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@Parameter(description = "Order ID") @PathVariable Long id) {
        Order order = orderService.getById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    // Retrieve all orders
    @Operation(summary = "Retrieve all orders")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.list();
        return ResponseEntity.ok(orders);
    }

    // Update an order
    @Operation(summary = "Update an order by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@Parameter(description = "Order ID") @PathVariable Long id, @RequestBody Order order) {
        order.setOrderId(id); // Ensure the ID is consistent
        orderService.updateById(order);
        return ResponseEntity.ok().build();
    }

    // Delete an order by ID
    @Operation(summary = "Delete an order by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@Parameter(description = "Order ID") @PathVariable Long id) {
        orderService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
