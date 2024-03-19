package com.group16.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.domain.dto.OrderFormDTO;
import com.group16.domain.po.Order;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderService extends IService<Order> {
    @Transactional
    Long createOrder(OrderFormDTO orderFormDTO);
}
