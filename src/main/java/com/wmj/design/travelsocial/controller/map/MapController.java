package com.wmj.design.travelsocial.controller.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    //点击前端中的watchmap
    @GetMapping("/watchmap")
    public String watchMap(){
        //返回相应的视图
        return "/trip/watchmap";
    }
    @GetMapping("/distance")
    public String getDistance(){
        return "/trip/distance";
    }
    @GetMapping("/planroute")
    public String planRoute(){
        return "/trip/planroute";
    }
}
