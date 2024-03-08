package com.group16.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.common.utils.BeanUtils;
import com.group16.order.domain.dto.OrderItemDTO;
import com.group16.order.domain.dto.ProductDTO;
import com.group16.order.domain.po.Product;
import com.group16.order.mapper.ProductMapper;
import com.group16.order.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Override
    public void deductStock(List<OrderItemDTO> itemDTOS) {
        String sqlStatement = "com.group16.order.mapper.ProductMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(itemDTOS, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            log.error("stock deduct error", e);
            return;
        }
        if (!r) {
            System.out.println("low stock");
        }
    }

    @Override
    public List<ProductDTO> queryProductByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ProductDTO.class);
    }

}
