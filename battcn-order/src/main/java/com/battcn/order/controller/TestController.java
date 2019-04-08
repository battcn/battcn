package com.battcn.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {


    @GetMapping("/test/limiter")
    public String test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3L);
        return "success";
    }

}
