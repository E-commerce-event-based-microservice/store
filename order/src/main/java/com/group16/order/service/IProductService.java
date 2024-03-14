package com.group16.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.order.domain.dto.OrderItemDTO;
import com.group16.order.domain.dto.ProductDTO;
import com.group16.order.domain.po.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> queryProductByIds(Collection<Long> ids);

    Product queryProductById(Long id);

//    void deductStock(List<OrderItemDTO> itemDTOS);

    void checkAndDeductStock(List<OrderItemDTO> itemDTOS);
}
