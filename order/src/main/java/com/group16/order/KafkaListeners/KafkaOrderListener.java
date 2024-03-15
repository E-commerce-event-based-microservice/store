package com.group16.order.KafkaListeners;

import cn.hutool.json.JSONUtil;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.service.IOrderService;
import com.group16.order.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class KafkaOrderListener {

    private final IProductService productService;
    private final IOrderService orderService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "orderCreate", groupId = "group16.order")
    void listen(String message) {
        try {
            // Deserializing order data using HuTool's JSONUtil
            OrderFormDTO orderFormDTO = JSONUtil.toBean(message, OrderFormDTO.class);
            System.out.println("Received order: " + orderFormDTO.toString());;
            // Create a new order
            Long orderID = orderService.createOrder(orderFormDTO);
            // Check inventory and attempt to reduce stock, update the order status upon success
            if (productService.checkAndDeductStock(orderFormDTO.getDetails())){
                // Send a success message to the "orderCreateSuccess" Kafka topic
                kafkaTemplate.send("orderCreateSuccess",  orderID.toString());
            }else {
                // Send a failure message to the "orderCreateFailed" Kafka topic
                kafkaTemplate.send("orderCreateFailed", "Insufficient stock for order: " + orderID);
                orderService.removeById(orderID);
            }
        } catch (Exception e) {
            System.out.println("Order processing failed: " + e.getMessage());
            kafkaTemplate.send("orderCreateFailed", "Order creation failed: " + e.getMessage());
        }
    }
}

