package com.group16.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.domain.po.OrderItem;
import com.group16.mapper.OrderItemMapper;
import com.group16.service.IOrderItemService;
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
