package com.group16.item.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity representing an item in the item table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Item ID.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * SKU name.
     */
    private String name;

    /**
     * Price in cents.
     */
    private Integer price;

    /**
     * Stock quantity.
     */
    private Integer stock;

    /**
     * Item image URL.
     */
    private String image;

    /**
     * Category name.
     */
    private String category;

    /**
     * Brand name.
     */
    private String brand;

    /**
     * Specifications.
     */
    private String spec;

    /**
     * Sales volume.
     */
    private Integer sold;

    /**
     * Number of comments.
     */
    private Integer commentCount;

    /**
     * Whether the item is an advertisement (true/false).
     */
    @TableField("isAD")
    private Boolean isAD;

    /**
     * Item status: 1 - Normal, 2 - Off shelf, 3 - Deleted.
     */
    private Integer status;

    /**
     * Creation time.
     */
    private LocalDateTime createTime;

    /**
     * Update time.
     */
    private LocalDateTime updateTime;

    /**
     * Creator ID.
     */
    private Long creater;

    /**
     * Updater ID.
     */
    private Long updater;
}
