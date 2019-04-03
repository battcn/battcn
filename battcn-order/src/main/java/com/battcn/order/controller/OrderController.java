package com.battcn.order.controller;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
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
@AllArgsConstructor
public class OrderController {


    //private final TokenStore tokenStore;

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

        OAuth2AuthenticationDetails details1 = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //String tokenValue = details1.getTokenValue();
        //OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        Object details = authentication.getDetails();
        return authentication.getPrincipal();
    }


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/info")
    public Principal info(Principal principal) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

}
