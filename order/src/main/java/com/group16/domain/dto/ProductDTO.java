package com.group16.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Product details")
public class ProductDTO {

    @Schema(description = "Product ID")
    private Long productId;

    @Schema(description = "Product name")
    private String name;

    @Schema(description = "Product price")
    private Double price;

    @Schema(description = "Product description")
    private String description;

    @Schema(description = "Stock number")
    private Integer stockNumber;

    @Schema(description = "Category ID")
    private Long categoryId;
}
