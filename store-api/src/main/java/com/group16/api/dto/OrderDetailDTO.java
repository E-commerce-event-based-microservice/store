package com.group16.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Data Transfer Object representing an order detail item, used in order processing and management.
 */
@Data // Lombok annotation for generating getters, setters, and other utility methods.
@Accessors(chain = true) // Allows for setter chaining.
@Schema(description = "Order Detail Item") // OpenAPI 3 annotation for class-level documentation.
public class OrderDetailDTO {
    @Schema(description = "Item ID") // Documentation for the field, indicating this is the identifier of the item.
    private Long itemId;

    @Schema(description = "Quantity of the item purchased") // Documentation for the field, representing how many units of the item are being ordered.
    private Integer num;
}
