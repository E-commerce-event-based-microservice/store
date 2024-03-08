package com.group16.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.api.dto.ItemDTO;

import com.group16.api.dto.OrderDetailDTO;
import com.group16.common.exception.BizIllegalException;
import com.group16.common.utils.BeanUtils;
import com.group16.item.domain.po.Item;
import com.group16.item.mapper.ItemMapper;
import com.group16.item.service.IItemService;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public void deductStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.group16.item.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            log.error("更新库存异常", e);
            return;
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }
}
