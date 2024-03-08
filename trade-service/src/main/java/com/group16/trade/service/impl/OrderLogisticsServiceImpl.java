package com.group16.trade.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.trade.domain.po.OrderLogistics;
import com.group16.trade.mapper.OrderLogisticsMapper;
import com.group16.trade.service.IOrderLogisticsService;
import org.springframework.stereotype.Service;

@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements IOrderLogisticsService {

}
