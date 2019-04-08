package com.battcn.auth;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RateLimiterTest {


    @Test
    public void test1() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i -> service.submit(() -> {
            ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:9000/order/test/limiter", String.class);
            System.out.println(entity.getBody());
        }));
        TimeUnit.SECONDS.sleep(30L);
    }

}
