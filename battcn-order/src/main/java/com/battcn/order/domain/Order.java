package com.battcn.order.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
@Table(name = "t_order")
public class Order {

    @Id
    private Long orderId;
    private String orderNo;
    private LocalDateTime createTime;


}
