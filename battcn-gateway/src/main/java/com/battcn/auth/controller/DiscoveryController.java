package com.battcn.auth.controller;

import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Levin
 */
@Controller
@RequestMapping("discovery")
public class DiscoveryController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<String> get(@RequestParam String serviceName) throws NacosException {
        return discoveryClient.getServices();
    }


}
