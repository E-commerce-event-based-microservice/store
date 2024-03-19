package com.group16.KafkaListeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.KafkaProducer.KafkaMessageService;
import com.group16.domain.dto.OrderFormDTO;
import com.group16.service.IOrderService;
import com.group16.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaOrderListener {

    private final IProductService productService;
    private final IOrderService orderService;
    private final KafkaMessageService kafkaMessageService;
    private final ObjectMapper objectMapper;

//    @Transactional
    @KafkaListener(topics = "orderCreate", groupId = "group16.order")
    void listen(String message) {
        try {
            System.out.println("Received order: " + message);
            OrderFormDTO orderFormDTO = objectMapper.readValue(message, OrderFormDTO.class);
            System.out.println("Received order: " + orderFormDTO);
            // Create a new order
            Long orderID = orderService.createOrder(orderFormDTO);
            // Check inventory and attempt to reduce stock, update the order status upon success
            if (productService.checkAndDeductStock(orderFormDTO.getDetails())){
                kafkaMessageService.sendMessage("orderCreateSuccess", orderID.toString());
            } else {
                kafkaMessageService.sendMessage("orderCreateFailed", "Insufficient stock for order: " + orderID);
                orderService.removeById(orderID);
            }
        } catch (Exception e) {
            System.out.println("Order processing failed: " + e.getMessage());
            kafkaMessageService.sendMessage("orderCreateFailed", "Order creation failed: " + e.getMessage());
        }
    }
}
