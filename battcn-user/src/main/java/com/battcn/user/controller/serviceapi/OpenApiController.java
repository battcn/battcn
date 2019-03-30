package com.battcn.user.controller.serviceapi;

import com.battcn.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@RestController
@RequestMapping("/service/api")
public class OpenApiController {


    @GetMapping("/users")
    public User user(@RequestParam String username) {
        return new User();
    }


}
