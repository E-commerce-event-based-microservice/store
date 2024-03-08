package com.group16.trade.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.trade.domain.po.OrderDetail;
import com.group16.trade.mapper.OrderDetailMapper;
import com.group16.trade.service.IOrderDetailService;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
