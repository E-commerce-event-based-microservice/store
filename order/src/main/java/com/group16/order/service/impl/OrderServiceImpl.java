package com.group16.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.common.exception.BadRequestException;
import com.group16.common.utils.UserContext;
import com.group16.order.domain.dto.OrderFormDTO;
import com.group16.order.domain.dto.OrderItemDTO;
import com.group16.order.domain.dto.ProductDTO;
import com.group16.order.domain.enums.OrderStatus;
import com.group16.order.domain.po.Order;
import com.group16.order.mapper.OrderMapper;
import com.group16.order.service.IOrderService;
import com.group16.order.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final IProductService productService;
    @Override
    @Transactional
    public Long createOrder(OrderFormDTO orderFormDTO) {
        // create a new po order
        Order order = new Order();
        // get the items from the orderFormDTO
        List<OrderItemDTO> itemDTOS= orderFormDTO.getDetails();
        // create a map of item id and the number of items
        Map<Long,Integer> itemNumMap = itemDTOS.stream()
                .collect(Collectors.toMap(OrderItemDTO::getItemId, OrderItemDTO::getNum));
        // get the item ids
        Set<Long> itemIds = itemNumMap.keySet();
        // get the items from the product service
        List<ProductDTO> items = productService.queryProductByIds(itemIds);
        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("some products do not exist");
        }
        // calculate the total price of the order
        double total = 0;
        for (ProductDTO item : items) {
            total += item.getPrice() * itemNumMap.get(item.getProductId());
        }
        order.setPrice(total);
        order.setStatus("PROCESSING");
        order.setUserId(UserContext.getUser());

        System.out.println(order);
        // insert the order into the database
        save(order);

        // deduct the stock of the items
        try {
            productService.deductStock(itemDTOS);
        } catch (Exception e) {
            throw new RuntimeException("no enough stock!");
        }
        return order.getOrderId();
    }
}
