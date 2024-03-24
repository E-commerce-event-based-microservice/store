package com.group16;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.domain.po.OrderItem;

import java.util.ArrayList;

public interface IOrderItemService extends IService<OrderItem> {
    ArrayList<OrderItem> listByOrderId(Long orderId);
    // Custom methods can be added here
}
