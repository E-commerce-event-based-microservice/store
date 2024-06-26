package com.group16.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("category")
public class Category {
    @TableId(type = IdType.AUTO)
    private Long categoryId;
    private String name;
    private String description;
}

