package com.battcn.auth.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * PreAuthorize 已经可以使用了
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    public List<String> query() {
        return Lists.newArrayList("order1", "order2");
    }
    @GetMapping("/test")
    public Object test() {
        Authentication authentication = getCurrentUserAuthentication();
        Object details = authentication.getDetails();
        return authentication.getPrincipal();
    }


    @GetMapping("/info")
    public Principal info(Principal principal) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

}
