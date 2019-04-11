package com.battcn.order.controller;

import com.battcn.framework.security.client.annotation.IgnoreAuthorize;
import com.battcn.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private final OrderService orderService;

    @IgnoreAuthorize
    @GetMapping("/test/order/{id}")
    public String test(@PathVariable Long id) {
        this.orderService.insertOrder(id);
        return "success";
    }


}
