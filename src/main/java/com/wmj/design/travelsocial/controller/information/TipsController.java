package com.wmj.design.travelsocial.controller.information;

import com.wmj.design.travelsocial.domain.information.Tips;
import com.wmj.design.travelsocial.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TipsController {

    @Autowired
    TipsService tipsService;

    @GetMapping("/tips")
    public String tips(ModelMap mmap){
        try{
            List<Tips> tipsList = tipsService.selectTipsList();
            mmap.put("tipsList",tipsList);
            return "information/tips";
        }catch (Exception e){
            e.printStackTrace();
            return "fail/404";
        }
    }
}
