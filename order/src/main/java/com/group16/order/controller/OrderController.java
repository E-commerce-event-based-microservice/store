package com.group16.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.po.Order;
import com.group16.order.domain.vo.OrderVO;
import com.group16.order.service.IOrderItemService;
import com.group16.order.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Management Interfaces")
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper; // Autowired ObjectMapper

    @PostMapping()
    @Operation(summary = "Create a new order")
    public ResponseEntity<?> createOrder(@RequestBody OrderFormDTO orderFormDTO) {
        try {
            String orderJson = objectMapper.writeValueAsString(orderFormDTO);
            kafkaTemplate.send("orderCreate", orderJson);
            return ResponseEntity.ok().body("Order received, wait for Email!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Order creation failed: " + e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Retrieve an order by ID")
    public OrderVO getOrderById(@Parameter(description = "Order ID") @PathVariable Long orderId) {
        Order order = orderService.getById(orderId);
        if (order != null) {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(order.getOrderId());
            orderVO.setPrice(order.getPrice());
            orderVO.setUserId(order.getUserId());
            orderVO.setStatus(order.getStatus());
            orderVO.setCreateTime(order.getDate());
            //Also show a List of OrderItems
            orderVO.setOrderItems(orderItemService.listByOrderId(orderId));
            return orderVO;
        }
        return null; // Or handle it differently
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
