package com.wmj.design.travelsocial.controller.menu;

import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.framework.web.controller.BaseController;
import com.wmj.design.travelsocial.framework.web.domain.AjaxResult;
import com.wmj.design.travelsocial.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 完善个人资料
 *
 * @author weimj
 * @date 2019/4/22
 */
@Controller
public class AddInfoController extends BaseController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/add_info")
    public String addInfo(){
        return  "/menu/add_info";
    }

    @GetMapping("/Demo")
    public String demo(){
        return "Demo";
    }

    @RequestMapping("/add_info")
    @ResponseBody
    public String addInfo(HttpSession session, String city, String userName,
                              String email, String birthday, String hobby, String sex){
        String msg;
        try{
            String phonenumber = (String) session.getAttribute("phonenumber");
            UserInfo ui= userInfoService.findByPhoneNumber(phonenumber);
            ui.setId(ui.getId());
            ui.setCity(city);
            ui.setUserName(userName);
            ui.setEmail(email);
            ui.setBirthday(birthday);
            ui.setHobby(hobby);
            ui.setSex(sex);
            if (userInfoService.updateUserInfo(ui) > 0){
                msg = "success";
                return msg;
            }else{
                msg = "fail";
                return msg;
            }
        }catch (Exception e){
            e.printStackTrace();
            msg = "fail";
            return msg;
        }
    }
}
