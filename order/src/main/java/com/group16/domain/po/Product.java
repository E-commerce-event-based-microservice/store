package com.group16.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableId(value = "productId", type = IdType.AUTO)
    private Long productId;
    private String name;
    private Double price;
    private String description;
    private Integer stockNumber;
    private Long categoryId;
}
