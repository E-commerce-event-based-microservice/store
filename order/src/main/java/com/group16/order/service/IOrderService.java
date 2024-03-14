package com.group16.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.po.Order;

public interface IOrderService extends IService<Order> {
    Long createOrder(OrderFormDTO orderFormDTO);
}
