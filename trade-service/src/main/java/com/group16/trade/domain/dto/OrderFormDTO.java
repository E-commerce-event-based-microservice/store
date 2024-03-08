package com.group16.trade.domain.dto;

import com.group16.api.dto.OrderDetailDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object representing the form submitted when placing an order.
 */
@Data // Lombok annotation for generating getters, setters, and other utility methods.
@Schema(description = "Order placement form entity") // OpenAPI 3 annotation for class-level documentation.
public class OrderFormDTO {
    @Schema(description = "Shipping address ID") // Documentation for the field.
    private Long addressId;

    @Schema(description = "Payment type") // Documentation for the field.
    private Integer paymentType;

    @Schema(description = "List of items ordered") // Documentation for the field.
    private List<OrderDetailDTO> details;
}
