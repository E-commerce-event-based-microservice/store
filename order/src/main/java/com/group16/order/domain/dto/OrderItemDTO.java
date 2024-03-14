package com.group16.order.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;


@Schema(description = "Order detail item")
@Data
@Accessors(chain = true)
public class OrderItemDTO {
    @Schema(description = "Product ID")
    private Long itemId;

    @Schema(description = "Quantity of product purchased")
    private Integer num;
}
