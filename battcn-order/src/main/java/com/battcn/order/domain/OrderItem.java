package com.battcn.order.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Levin
 */
@Data
@Table(name = "t_order_item")
public class OrderItem {
    @Id
    private Long orderId;
    private BigDecimal price;

}
