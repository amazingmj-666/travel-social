package com.wmj.design.travelsocial.controller.information;

import com.wmj.design.travelsocial.domain.information.Scenic;
import com.wmj.design.travelsocial.domain.information.ScenicImage;
import com.wmj.design.travelsocial.service.ScenicImageService;
import com.wmj.design.travelsocial.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ScenicController {
    @Autowired
    ScenicService scenicService;
    @Autowired
    ScenicImageService scenicImageService;
    @GetMapping("/scenic")
    public String scenic(ModelMap mmap){
        try{
            List<Scenic> scenicList = scenicService.selectScenicByRandom();
            mmap.put("scenicList",scenicList);
            return "/information/scenic/scenic";
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }
    @GetMapping("/detailed_scenic")
    public String detailedScenic(int scenicId,ModelMap mmap){
        try{
            List<ScenicImage> scenicImageList = scenicImageService.selectScenicImageByScenicId(scenicId);
            Scenic scenic = scenicService.selectScenicById(scenicId);
            mmap.put("scenicImageList",scenicImageList);
            mmap.put("scenic",scenic);
            return "/information/scenic/detailed_scenic";
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }
    @GetMapping("search_scenic_result")
    public String searchScenicResult(HttpSession session,ModelMap mmap){
        List<Scenic> scenicList = (List<Scenic>) session.getAttribute("scenicList");
        mmap.put("scenicList",scenicList);
        return "/information/scenic/search_scenic_result";
     }

    @RequestMapping("search_scenic")
    @ResponseBody
    public String searchScenic(String searchContent, HttpSession session){
        if (searchContent == null || searchContent.isEmpty()){
            return "fail";
        }else {
            List<Scenic> scenicList1 = scenicService.selectScenicListByCity(searchContent);
            List<Scenic> scenicList2 = scenicService.selectScenicListByScenicName(searchContent);
            if(scenicList1.isEmpty()){
                if (scenicList2.isEmpty()){
                    return "fail";
                }else{
                    session.setAttribute("scenicList",scenicList2);
                    return "success";
                }
            }else{
                session.setAttribute("scenicList",scenicList1);
                return "success";
            }
        }
    }
}
