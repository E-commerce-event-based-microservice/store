package com.group16.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.domain.dto.OrderFormDTO;
import com.group16.domain.po.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderService extends IService<Order> {
    @Transactional
    Long createOrder(OrderFormDTO orderFormDTO);

    List<Order> getOrdersByuserId(Long userId);

    Order getOrderById(Long orderId);
}
