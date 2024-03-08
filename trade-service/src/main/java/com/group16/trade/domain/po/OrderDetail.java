package com.group16.trade.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Entity representing a record in the order detail table.
 */
@Data // Lombok annotation for generating getters, setters, and other utility methods.
@EqualsAndHashCode(callSuper = false) // Specifies that this entity does not use its superclass' equals and hash code methods.
@Accessors(chain = true) // Enables fluent setter methods.
@TableName("order_detail") // Specifies the table name in the database.
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Order detail ID.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Order ID.
     */
    private Long orderId;

    /**
     * SKU item ID.
     */
    private Long itemId;

    /**
     * Purchase quantity.
     */
    private Integer num;

    /**
     * Item title.
     */
    private String name;

    /**
     * Item dynamic properties key-value set.
     */
    private String spec;

    /**
     * Price in cents.
     */
    private Integer price;

    /**
     * Item image URL.
     */
    private String image;

    /**
     * Creation time.
     */
    private LocalDateTime createTime;

    /**
     * Update time.
     */
    private LocalDateTime updateTime;
}
