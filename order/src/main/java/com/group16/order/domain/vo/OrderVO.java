package com.group16.order.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "VO of Order")
public class OrderVO {
    @Schema(description = "Order ID")
    private Long orderId;

    @Schema(description = "Total price")
    private Integer price;

    @Schema(description = "User ID")
    private Long userId;

    @Schema(description = "Status of the order, CANCELLED,PROCESSING,SHIPPED,DELIVERED")
    private Integer status;

    @Schema(description = "Creation time")
    private LocalDateTime createTime;
}