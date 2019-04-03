package com.battcn.auth.controller;


import com.alibaba.fastjson.JSONObject;
import com.battcn.auth.entity.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户信息
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 只有内部客户端才能有权限调用该接口
     *
     * @return 用户信息
     */
    @PreAuthorize("hasAuthority('inner:client')")
    @GetMapping("/info")
    public JSONObject info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        JSONObject result = new JSONObject();
        result.put("userId", authInfo.getUserId());
        result.put("phone", authInfo.getPhone());
        result.put("wxOpenId", authInfo.getWxOpenId());
        result.put("qqOpenId", authInfo.getQqOpenId());
        result.put("tenantId", authInfo.getTenantId());
        result.put("nickName", authInfo.getNickName());
        result.put("authorities", authInfo.getAuthorities());
        result.put("name", authInfo.getUsername());
        return result;
    }

}