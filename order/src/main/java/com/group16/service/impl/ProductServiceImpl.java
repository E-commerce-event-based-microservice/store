package com.group16.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.domain.dto.OrderItemDTO;
import com.group16.domain.po.Product;
import com.group16.mapper.ProductMapper;
import com.group16.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    public List<Product> queryProductByIds(Collection<Long> ids) {
        return listByIds(ids);
    }

    @Override
    public Product queryProductById(Long id) {
        return getById(id);
    }

    @Override
    @Transactional
    public boolean checkAndDeductStock(List<OrderItemDTO> itemDTOS) {
        for (OrderItemDTO item : itemDTOS) {
            Product product = queryProductById(item.getItemId());
            if (product.getStockNumber() >= item.getNum()) {
                product.setStockNumber(product.getStockNumber() - item.getNum());
                updateById(product);
            } else {
                System.out.println("Insufficient stock for product: " + product.getName() + " (" + product.getProductId() + ")");
                return false;
            }
        }

        return true;
    }

}
