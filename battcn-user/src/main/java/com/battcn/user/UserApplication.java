package com.battcn.user;

import com.battcn.framework.security.client.annotation.ClientEnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Levin
 */
@MapperScan("com.battcn.user.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@ClientEnableResourceServer
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
