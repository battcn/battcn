package com.battcn.auth.controller;


import com.alibaba.fastjson.JSONObject;
import com.battcn.auth.entity.AuthInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户信息
 *
 * @author Levin
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
    public JSONObject info(OAuth2Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        JSONObject result = new JSONObject();
        result.put("userId", authInfo.getUserId());
        result.put("mobile", authInfo.getMobile());
        result.put("openId", authInfo.getOpenId());
        result.put("tenantId", authInfo.getTenantId());
        result.put("nickName", authInfo.getNickName());
        result.put("authorities", authInfo.getAuthorities());
        result.put("name", authInfo.getUsername());
        return result;
    }

}