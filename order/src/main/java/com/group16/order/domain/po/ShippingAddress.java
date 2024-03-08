package com.group16.order.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("shippingAddress")
public class ShippingAddress {
    @TableId(type = IdType.AUTO)
    private Long shippingAddressId;
    private String fullName;
    private String phone;
    private String streetName;
    private String houseNumber;
    private String city;
    private String country;
    private Long userId;
}
