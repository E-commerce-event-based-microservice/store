package com.group16.order.KafkaListeners;

import cn.hutool.json.JSONUtil;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.service.IOrderService;
import com.group16.order.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaOrderListener {

    private final IProductService productService;
    private final IOrderService orderService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "orderCreate", groupId = "group16.order")
    //TODO: serialize
    void listen(String message) {
        try {
            // Deserializing order data using HuTool's JSONUtil
            OrderFormDTO orderFormDTO = JSONUtil.toBean(message, OrderFormDTO.class);
            // Create a new order
            Long orderID = orderService.createOrder(orderFormDTO);
            // Check inventory and attempt to reduce stock, update the order status upon success
            productService.checkAndDeductStock(orderFormDTO.getDetails());
            // Send a success message to the "orderCreateSuccess" Kafka topic
            kafkaTemplate.send("orderCreateSuccess",  orderID.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

