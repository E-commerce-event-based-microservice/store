package com.group16;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.domain.dto.OrderItemDTO;
import com.group16.domain.po.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> queryProductByIds(Collection<Long> ids);

    Product queryProductById(Long id);

//    void deductStock(List<OrderItemDTO> itemDTOS);

    boolean checkAndDeductStock(List<OrderItemDTO> itemDTOS);
}
