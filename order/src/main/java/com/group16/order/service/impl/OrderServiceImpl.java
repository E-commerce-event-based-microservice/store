package com.group16.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.enums.OrderStatus;
import com.group16.order.domain.po.Order;
import com.group16.order.domain.po.OrderItem;
import com.group16.order.mapper.OrderMapper;
import com.group16.order.service.IOrderItemService;
import com.group16.order.service.IOrderService;
import com.group16.order.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final IProductService productService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final IOrderItemService orderItemService;


    @Override
    @Transactional
    public Long createOrder(OrderFormDTO orderFormDTO) {
        Order order = new Order();

        // Assuming the authenticated username is the userId needed for the order.
        // Adjust this to fit your user identification logic.
//        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
//        order.setUserId(Long.valueOf(currentUserId));

        order.setBillingAddressId(orderFormDTO.getBillingAddressId());
        order.setShippingAddressId(orderFormDTO.getShippingAddressId());
        order.setDate(LocalDateTime.now()); // Using java.time.LocalDateTime
        order.setStatus(OrderStatus.PROCESSING.getStatus());

        // Calculate the total price of the order
        double total = orderFormDTO.getDetails().stream().mapToDouble(item -> {
            double itemPrice = productService.queryProductById(item.getItemId()).getPrice();
            return itemPrice * item.getNum();
        }).sum();
        order.setPrice(total);

        // Insert the order into the database
        save(order);

        // Convert the details from the order form into Orderitems and save it in the orderitems table
        orderFormDTO.getDetails().forEach(item -> {
            // Save the order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setProductId(item.getItemId());
            orderItem.setQuantity(item.getNum());
            orderItem.setPrice(productService.queryProductById(item.getItemId()).getPrice());
            orderItemService.save(orderItem);
        });

        System.out.println("Order created: " + order.getOrderId());

        return order.getOrderId();
    }
}