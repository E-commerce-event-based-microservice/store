package com.group16.order.controller;

import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.po.Order;
import com.group16.order.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// REST controller for handling order-related requests
@Tag(name = "Order Management Interfaces") // Swagger tag for order management
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    // Create a new order
    @Operation(summary = "Create a new order")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderFormDTO orderFormDTO) {
        try {
            Long orderId = orderService.createOrder(orderFormDTO);
            Map<String, Long> response = new HashMap<>();
            response.put("orderId", orderId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
