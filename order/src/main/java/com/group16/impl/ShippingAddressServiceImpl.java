package com.group16.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.domain.po.ShippingAddress;
import com.group16.mapper.ShippingAddressMapper;
import com.group16.IShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl extends ServiceImpl<ShippingAddressMapper, ShippingAddress> implements IShippingAddressService {
}
