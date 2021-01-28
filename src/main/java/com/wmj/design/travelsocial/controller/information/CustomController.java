package com.wmj.design.travelsocial.controller.information;

import com.wmj.design.travelsocial.domain.information.Custom;
import com.wmj.design.travelsocial.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomController {
    @Autowired
    CustomService customService;
    @GetMapping("/custom")
    public String custom(ModelMap mmap){
        try{
            List<Custom> customList = customService.selectCustomByRandom();
            mmap.put("customList",customList);
            return "/information/custom";
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }
    @GetMapping("/custom_content")
    public String customContent(int customId,ModelMap mmap){
        Custom custom = new Custom();
        custom.setId(customId);
        custom =customService.selectCustomById(custom);
        mmap.put("custom",custom);
        return "/information/custom_content";
    }
    @GetMapping("/search_custom_result")
    public String searchCustom(HttpSession session, ModelMap mmap){
        List<Custom> customList = (List<Custom>) session.getAttribute("customList");
        mmap.put("customList",customList);
        return "/information/custom/search_result";
    }


    @RequestMapping("/search_custom")
    @ResponseBody
    public String searchCustom(String country, ModelMap mmap,HttpSession session){
        try{
            System.out.println(country);
            Custom custom = new Custom();
            custom.setCountry(country);
            List<Custom> customList = customService.selectCustomListByCountry(custom);
            if (customList.isEmpty()){
                return "fail";
            }else{
                session.setAttribute("customList",customList);
                mmap.put("customList",customList);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
