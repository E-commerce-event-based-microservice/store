package com.group16.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.common.utils.BeanUtils;
import com.group16.inventory.domain.po.OrderItem;
import com.group16.inventory.domain.po.Product;
import com.group16.inventory.mapper.ProductMapper;
import com.group16.inventory.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
//    @Override
//    public void deductStock(List<OrderItem> items) {
//        String sqlStatement = "com.group16.inventory.mapper.ProductMapper.updateStock";
//        boolean r = false;
//        try {
//            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
//        } catch (Exception e) {
//            log.error("stock deduct error", e);
//            return;
//        }
//        if (!r) {
//            System.out.println("low stock");
//        }
//    }

    @Override
    public List<Product> queryProductByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), Product.class);
    }

}
