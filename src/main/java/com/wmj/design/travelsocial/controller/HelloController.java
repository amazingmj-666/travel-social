package com.wmj.design.travelsocial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
/**
 * Demo class
 *
 * @author weimj
 * @date 2019/3/27
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String,Object> map){
        map.put("hello","欢迎来到HTML页面");
        return "hello";
    }
}
