package com.group16.trade.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents the Order entity associated with the 'order' table in the database.
 */
@Data // Lombok annotation for generating getters, setters, and other utility methods.
@EqualsAndHashCode(callSuper = false) // Specifies that this entity does not use its superclass' equals and hash code methods.
@Accessors(chain = true) // Enables fluent setter methods.
@TableName("`order`") // Specifies the table name in the database.
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Order ID.
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * Total amount in cents.
     */
    private Integer totalFee;

    /**
     * Payment type. 1 for Alipay, 2 for WeChat, 3 for balance deduction.
     */
    private Integer paymentType;

    /**
     * User ID.
     */
    private Long userId;

    /**
     * Status of the order:
     * 1 - Unpaid,
     * 2 - Paid, not shipped,
     * 3 - Shipped, not confirmed,
     * 4 - Confirmed received, transaction successful,
     * 5 - Transaction cancelled, order closed,
     * 6 - Transaction finished, reviewed.
     */
    private Integer status;

    /**
     * Creation time.
     */
    private LocalDateTime createTime;

    /**
     * Payment time.
     */
    private LocalDateTime payTime;

    /**
     * Shipping time.
     */
    private LocalDateTime consignTime;

    /**
     * Transaction completion time.
     */
    private LocalDateTime endTime;

    /**
     * Transaction close time.
     */
    private LocalDateTime closeTime;

    /**
     * Review time.
     */
    private LocalDateTime commentTime;

    /**
     * Update time.
     */
    private LocalDateTime updateTime;
}
