package com.group16.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.order.domain.po.OrderItem;
import com.group16.order.mapper.OrderItemMapper;
import com.group16.order.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public ArrayList<OrderItem> listByOrderId(Long orderId) {
        return new ArrayList<>(orderItemMapper.selectByOrderId(orderId));
    }
}
