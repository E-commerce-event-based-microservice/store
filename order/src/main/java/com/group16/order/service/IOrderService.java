package com.group16.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.po.Order;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderService extends IService<Order> {
    @Transactional
    Long createOrder(OrderFormDTO orderFormDTO);
}
