package com.group16.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("orders")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long orderId;
    private String status;
    private LocalDateTime date;
    private Double price;
    private Long userId;
    private Long shippingAddressId;
    private Long billingAddressId;
    private String email;


    public void addOrderItem(Long itemId, Integer num) {
        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(itemId);
        orderItem.setQuantity(num);
        orderItem.setOrderId(this.orderId);
        orderItems.add(orderItem);
    }
}
