package com.group16.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Data Transfer Object for items, used for transferring item data between processes or across networks.
 */
@Data
@Schema(description = "Item Entity") // OpenAPI 3 annotation for class-level documentation.
public class ItemDTO {
    @Schema(description = "Item ID") // Documentation for the field.
    private Long id;

    @Schema(description = "SKU Name") // Documentation for the field.
    private String name;

    @Schema(description = "Price (in cents)") // Documentation for the field.
    private Integer price;

    @Schema(description = "Stock quantity") // Documentation for the field.
    private Integer stock;

    @Schema(description = "Item image URL") // Documentation for the field.
    private String image;

    @Schema(description = "Category name") // Documentation for the field.
    private String category;

    @Schema(description = "Brand name") // Documentation for the field.
    private String brand;

    @Schema(description = "Specifications") // Documentation for the field.
    private String spec;

    @Schema(description = "Sales volume") // Documentation for the field.
    private Integer sold;

    @Schema(description = "Number of comments") // Documentation for the field.
    private Integer commentCount;

    @Schema(description = "Whether the item is an advertisement (true/false)") // Documentation for the field.
    private Boolean isAD;

    @Schema(description = "Item status: 1-Normal, 2-Off shelf, 3-Deleted") // Documentation for the field.
    private Integer status;
}
