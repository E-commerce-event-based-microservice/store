package com.group16.order.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.common.utils.UserContext;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.po.Order;
import com.group16.order.mapper.OrderMapper;
import com.group16.order.service.IOrderService;
import com.group16.order.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final IProductService productService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Transactional
    public Long createOrder(OrderFormDTO orderFormDTO) {
        // creating new order object
        Order order = new Order();
        // fill the order object with the orderFormDTO
        order.setUserId(UserContext.getUser());
        // get the user id from the UserContext
        order.setBillingAddressId(orderFormDTO.getBillingAddressId());
        order.setShippingAddressId(orderFormDTO.getShippingAddressId());
        order.setDate(DateTime.now());
        order.setStatus("PROCESSING");

        // calculate the total price of the order
        double total = orderFormDTO.getDetails().stream().mapToDouble(item -> {
            // get the price of the item from the product service
            double itemPrice = productService.queryProductById(item.getItemId()).getPrice();
            return itemPrice * item.getNum();
        }).sum();
        order.setPrice(total);

        // insert the order into the database
        save(order);

        // serialize the order object into a JSON string
        try {
            String orderJson = JSONUtil.toJsonStr(orderFormDTO);
            // Send the order as a JSON string to the Kafka topic.
            kafkaTemplate.send("order", orderJson);
        } catch (Exception e) {
            throw new RuntimeException("serialization error");
        }

        // Order ID auto generated by the database
        return order.getOrderId();
    }
}
