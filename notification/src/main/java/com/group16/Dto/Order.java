package com.group16.Dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long orderId;
    private String status;
    private LocalDateTime date;
    private Double price;
    private Long userId;
    private Long shippingAddressId;
    private Long billingAddressId;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
