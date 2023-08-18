package com.anlythree.anlynacos.provider.demo.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;



@Service(version = "1.0.0")
public class HelloServiceBImpl implements HelloServiceB {

    @Autowired
    InetUtils inetUtils;

    @Override
    public String hello(String name) {
        return "B" + "[" + inetUtils.findFirstNonLoopbackAddress().getHostAddress() + "]";
    }

}
