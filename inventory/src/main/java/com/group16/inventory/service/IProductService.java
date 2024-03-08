package com.group16.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.inventory.domain.po.OrderItem;
import com.group16.inventory.domain.po.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> queryProductByIds(Collection<Long> ids);

}
