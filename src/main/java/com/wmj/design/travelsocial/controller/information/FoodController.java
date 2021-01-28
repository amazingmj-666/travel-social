package com.wmj.design.travelsocial.controller.information;

import com.wmj.design.travelsocial.domain.information.Food;
import com.wmj.design.travelsocial.domain.information.FoodImage;
import com.wmj.design.travelsocial.service.FoodImageService;
import com.wmj.design.travelsocial.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    FoodImageService foodImageService;
    @GetMapping("/food")
    public String food(ModelMap mmap){
        try{
            List<Food> foodList = foodService.selectFoodByRandom();
            mmap.put("foodList",foodList);
            return "/information/food/food";
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }
    @GetMapping("/detailed_food")
    public String detailedFood(int foodId,ModelMap mmap){
        try{
            List<FoodImage> foodImageList = foodImageService.selectFoodImageByFoodId(foodId);
            Food food = foodService.selectFoodById(foodId);
            mmap.put("foodImageList",foodImageList);
            mmap.put("food",food);
            return "/information/food/detailed_food";
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }
    @GetMapping("search_food_result")
    public String searchScenicResult(HttpSession session,ModelMap mmap){
        List<Food> foodList = (List<Food>) session.getAttribute("foodList");
        mmap.put("foodList",foodList);
        return "/information/food/search_food_result";
    }

    @RequestMapping("search_food")
    @ResponseBody
    public String searchScenic(String searchContent, HttpSession session){
        if (searchContent == null || searchContent.isEmpty()){
            return "fail";
        }else {
            List<Food> foodList1 = foodService.selectFoodListByCity(searchContent);
            List<Food> foodList2 = foodService.selectFoodListByName(searchContent);
            if(foodList1.isEmpty()){
                if (foodList2.isEmpty()){
                    return "fail";
                }else{
                    session.setAttribute("foodList",foodList2);
                    return "success";
                }
            }else{
                session.setAttribute("foodList",foodList1);
                return "success";
            }
        }
    }
}
