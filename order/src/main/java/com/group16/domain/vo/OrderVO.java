package com.group16.domain.vo;

import com.group16.domain.po.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Schema(name = "VO of Order")
public class OrderVO {
    @Schema(description = "Order ID")
    private Long orderId;

    @Schema(description = "Total price")
    private Double price;

    @Schema(description = "User ID")
    private Long userId;

    @Schema(description = "Status of the order, CANCELLED,PROCESSING,SHIPPED,DELIVERED")
    private String status;

    @Schema(description = "Creation time")
    private LocalDateTime createTime;

    @Schema(description = "Order items")
    private ArrayList<OrderItem> orderItems;
}