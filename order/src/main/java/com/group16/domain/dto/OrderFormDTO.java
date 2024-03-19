package com.group16.domain.dto;

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
    @Schema(description = "billingAddressId")
    private Long billingAddressId;
    @Schema(description = "shippingAddressId")
    private Long shippingAddressId;
}
