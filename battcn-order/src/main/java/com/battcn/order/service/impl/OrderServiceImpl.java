package com.battcn.order.service.impl;

import com.battcn.order.domain.Order;
import com.battcn.order.domain.OrderItem;
import com.battcn.order.mapper.OrderItemMapper;
import com.battcn.order.mapper.OrderMapper;
import com.battcn.order.service.OrderService;
import io.shardingsphere.core.keygen.KeyGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Levin
 * @since 2019-04-11
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final KeyGenerator keyGenerator;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertOrder(Long id) {

        Order order = new Order();
        order.setOrderId(keyGenerator.generateKey().longValue());
        order.setOrderNo(UUID.randomUUID().toString());
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insertSelective(order);
        //int i = 10 / 0;
        OrderItem item = new OrderItem();
        item.setOrderId(order.getOrderId());
        item.setPrice(new BigDecimal("1"));
        orderItemMapper.insertSelective(item);
    }
}
