package com.battcn.user;

import com.battcn.framework.security.client.annotation.ClientEnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@EnableDiscoveryClient
@SpringBootApplication
@ClientEnableResourceServer
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
