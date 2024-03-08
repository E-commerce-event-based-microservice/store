package com.group16.order.domain.dto;

import com.group16.order.domain.po.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Tag(name = "Entity of Order Form")
@Accessors(chain = true)
public class OrderFormDTO {
    @Schema(description = "List of ordered items")
    private List<OrderItemDTO> details;
}
