package com.battcn.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Levin
 */
@RefreshScope
@Controller
@RequestMapping("config")
public class ConfigController {

    @Value("${test1}")
    private String test1;

    @RequestMapping(value = "/test1", method = GET)
    @ResponseBody
    public String test1() {
        return test1;
    }
}