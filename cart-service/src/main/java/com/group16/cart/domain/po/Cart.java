package com.group16.cart.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Shopping cart item id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * User id
     */
    private Long userId;

    /**
     * SKU product id
     */
    private Long itemId;

    /**
     * Purchase quantity
     */
    private Integer num;

    /**
     * Product title
     */
    private String name;

    /**
     * Product dynamic attributes key-value set
     */
    private String spec;

    /**
     * Price, unit: cents
     */
    private Integer price;

    /**
     * Product image
     */
    private String image;

    /**
     * Creation time
     */
    private LocalDateTime createTime;

    /**
     * Update time
     */
    private LocalDateTime updateTime;
}
