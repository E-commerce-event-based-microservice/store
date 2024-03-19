package com.group16.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orderItem")
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Long orderItemId;
    private Integer quantity;
    private Double price;
    private Long orderId;
    private Long productId;
}
