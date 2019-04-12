package com.battcn.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Levin
 * @since 2019-04-03
 **/
@FeignClient(name = "battcn-user/users", decode404 = true)
public interface RemoteUserClient {

    @GetMapping("/{username}")
    SysUser loadUserByUsername(@PathVariable("username") String username, @RequestHeader(value = "from",defaultValue = "y") String from);

}
