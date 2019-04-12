package com.battcn.user.controller;

import com.battcn.framework.security.client.annotation.InnerService;
import com.battcn.user.entity.SysUser;
import com.battcn.user.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PreAuthorize 已经可以使用了
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final SysUserService sysUserService;


    @InnerService
    @GetMapping("/{username}")
    public SysUser loadUserByUsername(@PathVariable String username) {
        return this.sysUserService.loadUserByUsername(username);
    }


}
