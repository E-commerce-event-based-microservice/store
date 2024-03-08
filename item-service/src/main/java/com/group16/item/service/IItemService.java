package com.group16.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group16.api.dto.ItemDTO;

import com.group16.api.dto.OrderDetailDTO;
import com.group16.item.domain.po.Item;


import java.util.Collection;
import java.util.List;


public interface IItemService extends IService<Item> {

    void deductStock(List<OrderDetailDTO> items);

    List<ItemDTO> queryItemByIds(Collection<Long> ids);
}
