package com.group16.trade.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Value Object for displaying order information on the webpage.
 */
@Data // Lombok annotation for generating getters, setters, and other utility methods.
@Schema(description = "Order Page VO") // OpenAPI 3 annotation for class-level documentation.
public class OrderVO {
    @Schema(description = "Order ID") // Documentation for the field.
    private Long id;

    @Schema(description = "Total amount, unit: cents") // Documentation for the field.
    private Integer totalFee;

    @Schema(description = "Payment type: 1 for Alipay, 2 for WeChat, 3 for balance deduction") // Documentation for the field.
    private Integer paymentType;

    @Schema(description = "User ID") // Documentation for the field.
    private Long userId;

    @Schema(description = "Order status: 1 for unpaid, 2 for paid but not shipped, 3 for shipped but not confirmed, 4 for confirmed receipt and transaction successful, 5 for transaction cancelled and order closed, 6 for transaction ended and reviewed") // Documentation for the field.
    private Integer status;

    @Schema(description = "Creation time") // Documentation for the field.
    private LocalDateTime createTime;

    @Schema(description = "Payment time") // Documentation for the field.
    private LocalDateTime payTime;

    @Schema(description = "Shipping time") // Documentation for the field.
    private LocalDateTime consignTime;

    @Schema(description = "Transaction completion time") // Documentation for the field.
    private LocalDateTime endTime;

    @Schema(description = "Transaction close time") // Documentation for the field.
    private LocalDateTime closeTime;

    @Schema(description = "Review time") // Documentation for the field.
    private LocalDateTime commentTime;
}
