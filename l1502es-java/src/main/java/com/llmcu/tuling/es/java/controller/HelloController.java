package com.llmcu.tuling.es.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/8 19:32
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public String sayHello(){
        return "hello";
    }
}
