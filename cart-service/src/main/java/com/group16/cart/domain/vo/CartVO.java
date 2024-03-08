package com.group16.cart.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Shopping Cart VO (Value Object) Entity") // Updated from @ApiModel
public class CartVO {
    @Schema(description = "Shopping cart item id") // Updated from @ApiModelProperty
    private Long id;

    @Schema(description = "SKU product id") // Updated
    private Long itemId;

    @Schema(description = "Purchase quantity") // Updated
    private Integer num;

    @Schema(description = "Product title") // Updated
    private String name;

    @Schema(description = "Product dynamic attributes key-value set") // Updated
    private String spec;

    @Schema(description = "Price, unit: cents") // Updated
    private Integer price;

    @Schema(description = "Latest product price") // Updated
    private Integer newPrice;

    @Schema(description = "Latest product status") // Updated
    private Integer status;

    @Schema(description = "Latest product stock") // Updated
    private Integer stock;

    @Schema(description = "Product image") // Updated
    private String image;

    @Schema(description = "Creation time") // Updated
    private LocalDateTime createTime;
}
