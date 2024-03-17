package com.group16.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group16.order.domain.po.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @Select("select * from orderItem where orderId = #{orderId}")
    List<OrderItem> selectByOrderId(Long orderId);
}
