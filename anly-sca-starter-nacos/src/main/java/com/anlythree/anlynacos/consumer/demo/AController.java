package com.anlythree.anlynacos.consumer.demo;

import com.anlythree.anlynacos.consumer.demo.service.HelloServiceB;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RestController("")
public class AController {


    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    @DubboReference(application = "${dubbo.application.id}", version = "1.0.0")
    private HelloServiceB helloServiceB;

    @Value("${name:123}")
    private String name;


    @Resource
    RestTemplate restTemplate;

    @GetMapping("/a")
    public String a(HttpServletRequest request) {
        return "A"+ "[" + request.getLocalAddr() + "]" + " -> " +
                restTemplate.getForObject("http://localhost:8080/b", String.class);
    }

    @GetMapping("/dubbo")
    public String dubbo(HttpServletRequest request) {
        return "A"+ "[" + request.getLocalAddr() + "]" + " -> " +
                helloServiceB.hello("A");
    }

//    @PostConstruct
//    private void printConfig() {
//        EXECUTOR_SERVICE.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(name);
//            }
//        },0,2, TimeUnit.SECONDS);
//    }
}
