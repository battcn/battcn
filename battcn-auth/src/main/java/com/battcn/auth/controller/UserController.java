package com.battcn.auth.controller;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


/**
 * - Description:Rest APIs
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping
    public List<String> listUser() {
        return Lists.newArrayList("test1", "test2");
    }

    @GetMapping("/info")
    public Principal info(Principal principal) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

}