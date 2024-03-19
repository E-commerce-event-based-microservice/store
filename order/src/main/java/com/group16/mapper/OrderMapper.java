package com.group16.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group16.domain.po.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
