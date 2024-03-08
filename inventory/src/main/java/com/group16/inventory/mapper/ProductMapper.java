package com.group16.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group16.inventory.domain.po.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}