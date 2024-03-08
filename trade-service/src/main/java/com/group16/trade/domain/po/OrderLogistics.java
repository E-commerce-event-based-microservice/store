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
 * Entity representing a record in the order logistics table.
 * This entity stores the logistics information for each order.
 */
@Data // Lombok annotation for generating getters, setters, and other utility methods.
@EqualsAndHashCode(callSuper = false) // Specifies that this entity does not use its superclass' equals and hash code methods.
@Accessors(chain = true) // Enables fluent setter methods.
@TableName("order_logistics") // Specifies the table name in the database.
public class OrderLogistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Order ID, having a one-to-one relationship with the order table.
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private Long orderId;

    /**
     * Logistics tracking number.
     */
    private String logisticsNumber;

    /**
     * Name of the logistics company.
     */
    private String logisticsCompany;

    /**
     * Recipient name.
     */
    private String contact;

    /**
     * Recipient mobile phone number.
     */
    private String mobile;

    /**
     * Province.
     */
    private String province;

    /**
     * City.
     */
    private String city;

    /**
     * District/Town.
     */
    private String town;

    /**
     * Street address.
     */
    private String street;

    /**
     * Creation time of the record.
     */
    private LocalDateTime createTime;

    /**
     * Last update time of the record.
     */
    private LocalDateTime updateTime;
}
