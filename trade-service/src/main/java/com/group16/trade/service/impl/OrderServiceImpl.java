package com.group16.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.api.client.CartClient;
import com.group16.api.client.ItemClient;
import com.group16.api.dto.ItemDTO;
import com.group16.api.dto.OrderDetailDTO;
import com.group16.common.exception.BadRequestException;
import com.group16.common.utils.UserContext;
import com.group16.trade.domain.dto.OrderFormDTO;
import com.group16.trade.domain.po.Order;
import com.group16.trade.domain.po.OrderDetail;
import com.group16.trade.mapper.OrderMapper;
import com.group16.trade.service.IOrderDetailService;
import com.group16.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for handling order operations including creation and updating order statuses.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final ItemClient itemClient;
    private final IOrderDetailService detailService;
    private final CartClient cartClient;

    @Override
    @Transactional // Marks this method to run within a transactional context.
    public Long createOrder(OrderFormDTO orderFormDTO) {
        // Initialize new order
        Order order = new Order();

        // Retrieve order details from the form
        List<OrderDetailDTO> detailDTOS = orderFormDTO.getDetails();

        // Create a map of item IDs and their quantities
        Map<Long, Integer> itemNumMap = detailDTOS.stream()
                .collect(Collectors.toMap(OrderDetailDTO::getItemId, OrderDetailDTO::getNum));
        Set<Long> itemIds = itemNumMap.keySet();

        // Fetch item details from the item service
        List<ItemDTO> items = itemClient.queryItemByIds(itemIds);
        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("Some items do not exist");
        }

        // Calculate total fee based on item prices and quantities
        int total = items.stream()
                .mapToInt(item -> item.getPrice() * itemNumMap.get(item.getId()))
                .sum();
        order.setTotalFee(total)
                .setPaymentType(orderFormDTO.getPaymentType())
                .setUserId(UserContext.getUser()) // Retrieve user ID from the UserContext
                .setStatus(1) // Status 1 typically represents an order that has been placed but not yet paid
                .setCreateTime(LocalDateTime.now());

        // Save the new order to the database
        save(order);

        // Save order details
        List<OrderDetail> details = buildDetails(order.getId(), items, itemNumMap);
        detailService.saveBatch(details);

        // Remove ordered items from the cart
        cartClient.deleteCartItemByIds(itemIds);

        // Deduct stock for the ordered items
        itemClient.deductStock(detailDTOS);

        return order.getId(); // Return the ID of the newly created order
    }

    @Override
    public void markOrderPaySuccess(Long orderId) {
        Order order = getById(orderId);
        if (order == null) {
            throw new BadRequestException("Order does not exist");
        }
        // Update order status to indicate payment success
        order.setStatus(2) // Status 2 typically means paid but not shipped
                .setPayTime(LocalDateTime.now());
        updateById(order);
    }

    // Utility method to build order detail entities from item details
    private List<OrderDetail> buildDetails(Long orderId, List<ItemDTO> items, Map<Long, Integer> numMap) {
        return items.stream().map(item -> {
            OrderDetail detail = new OrderDetail()
                    .setOrderId(orderId)
                    .setItemId(item.getId())
                    .setNum(numMap.get(item.getId()))
                    .setName(item.getName())
                    .setSpec(item.getSpec())
                    .setPrice(item.getPrice())
                    .setImage(item.getImage())
                    .setCreateTime(LocalDateTime.now());
            return detail;
        }).collect(Collectors.toList());
    }
}
