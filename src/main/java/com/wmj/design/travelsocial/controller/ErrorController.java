package com.wmj.design.travelsocial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/search_custom_null")
    public String searchCustomNull(){
        return "fail/search_custom_null";
    }

    @GetMapping("/search_scenic_null")
    public String searchScenicNull(){
        return "fail/search_scenic_null";
    }

    @GetMapping("/search_food_null")
    public String searchFoodNull(){
        return "fail/search_food_null";
    }

    @GetMapping("/no_dynamics")
    public String noDynamics(){
        return "fail/no_dynamics";
    }
}
