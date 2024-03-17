package com.group16.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group16.order.domain.dto.OrderItemDTO;
import com.group16.order.domain.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Update("UPDATE product SET stockNumber = stockNumber - #{num} WHERE productId = #{itemId}")
    void updateStock(OrderItemDTO orderItemDTO);
}
