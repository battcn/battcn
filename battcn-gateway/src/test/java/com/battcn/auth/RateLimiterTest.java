package com.battcn.auth;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {


    @Test
    public void test1() {
        CompletableFuture.runAsync(() -> System.out.println(new RestTemplate().getForEntity("http://localhost:9000/order/test/limiter", String.class).getBody()));
        CompletableFuture.runAsync(() -> System.out.println(new RestTemplate().getForEntity("http://localhost:9000/order/test/limiter", String.class).getBody()));
        CompletableFuture.runAsync(() -> System.out.println(new RestTemplate().getForEntity("http://localhost:9000/order/test/limiter", String.class).getBody()));
        try {
            TimeUnit.SECONDS.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
