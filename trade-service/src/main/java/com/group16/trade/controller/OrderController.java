package com.group16.trade.controller;

import com.group16.common.utils.BeanUtils;
import com.group16.trade.domain.dto.OrderFormDTO;
import com.group16.trade.domain.vo.OrderVO;
import com.group16.trade.service.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing orders, providing endpoints to query, create, and update orders.
 */
@Tag(name = "Order Management Interface") // Documentation for the controller
@RestController // Marks the class as a Spring MVC web controller
@RequestMapping("/orders") // Maps HTTP requests to handler methods of MVC and REST controllers
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments (final fields)
public class OrderController {
    private final IOrderService orderService; // Injects order service

    @Operation(summary = "Query order by ID") // Documentation for the API operation
    @GetMapping("{id}") // HTTP GET mapping for querying an order by ID
    public OrderVO queryOrderById(@Parameter(description = "Order ID") @PathVariable("id") Long orderId) {
        // Uses BeanUtils to convert the order from the service to an OrderVO object
        return BeanUtils.copyBean(orderService.getById(orderId), OrderVO.class);
    }

    @Operation(summary = "Create an order") // Documentation for the API operation
    @PostMapping // HTTP POST mapping for creating a new order
    public Long createOrder(@RequestBody OrderFormDTO orderFormDTO) {
        // Calls the service method to create a new order and returns its ID
        return orderService.createOrder(orderFormDTO);
    }

    @Operation(summary = "Mark an order as paid") // Documentation for the API operation
    @PutMapping("/{orderId}") // HTTP PUT mapping for updating an order's status to paid
    public void markOrderPaySuccess(@Parameter(description = "Order ID") @PathVariable("orderId") Long orderId) {
        // Calls the service method to mark the order as successfully paid
        orderService.markOrderPaySuccess(orderId);
    }
}
