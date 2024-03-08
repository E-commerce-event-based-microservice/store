package com.group16.trade.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.trade.domain.dto.OrderFormDTO;
import com.group16.trade.domain.po.Order;


public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
