package com.group16.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group16.inventory.domain.po.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
