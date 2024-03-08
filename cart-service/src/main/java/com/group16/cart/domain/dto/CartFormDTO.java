package com.group16.cart.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "New shopping cart product form entity") // Updated from @ApiModel
public class CartFormDTO {
    @Schema(description = "Product id") // Updated from @ApiModelProperty
    private Long itemId;

    @Schema(description = "Product title") // Updated
    private String name;

    @Schema(description = "Product dynamic properties key-value set") // Updated
    private String spec;

    @Schema(description = "Price, unit: cents") // Updated
    private Integer price;

    @Schema(description = "Product image") // Updated
    private String image;
}
