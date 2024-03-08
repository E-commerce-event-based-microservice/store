package com.group16.inventory.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long orderId;
    private String status;
    private Date date;
    private Double price;
    private Long userId;
    private Long shippingAddressId;
    private Long billingAddressId;
}
