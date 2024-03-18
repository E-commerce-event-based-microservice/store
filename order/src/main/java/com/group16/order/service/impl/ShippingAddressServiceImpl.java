package com.group16.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.order.domain.po.ShippingAddress;
import com.group16.order.mapper.ShippingAddressMapper;
import com.group16.order.service.IShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl extends ServiceImpl<ShippingAddressMapper, ShippingAddress> implements IShippingAddressService {
}
