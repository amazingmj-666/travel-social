package com.wmj.design.travelsocial.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.KeyUtil;
import com.wmj.design.travelsocial.utils.Md5Utils;
import com.wmj.design.travelsocial.utils.RedisUtil;
import com.wmj.design.travelsocial.utils.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author weimj
 * @date 2019/3/28
 */
@Controller
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/login")
    public String loginHtml(){
        return "login";
    }

    @GetMapping("checkPhoneNumberIsExist")
    @ResponseBody
    public boolean checkPhoneNumberIsExist(String phonenumber){
        UserInfo userInfo = userInfoService.findByPhoneNumber(phonenumber);
        if (!StringUtils.isEmpty(userInfo)){
            return true;
        }
        return false;
    }

    @GetMapping("checkPasswordIsExist")
    @ResponseBody
    public boolean checkPasswordIsExist(String phonenumber,String password){
        UserInfo userInfo = userInfoService.findByPhoneNumber(phonenumber);
        if (userInfo.getPassword().equals(Md5Utils.hash(password))){
            return true;
        }
        return false;
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@Param("phonenumber")String phonenumber, @Param("password") String password, HttpSession session){
        try{
            UserInfo userInfo = new UserInfo();
            userInfo.setPhonenumber(phonenumber);
            userInfo.setPassword(Md5Utils.hash(password));
            if (userInfoService.findByUserInfo(userInfo) != null){
                session.setAttribute("phonenumber",phonenumber);
                String uuId = UUIDUtil.getUUID();
                String loginKey = KeyUtil.getLoginSuccessRedisKey(phonenumber);
                System.out.println("登录成功的key为:" + loginKey);
                String sessionId = KeyUtil.getSessionId(phonenumber, uuId);
                System.out.println("登录成功的sessionId为:" + sessionId);
                RedisUtil.set(loginKey, sessionId);
                return "success";
            }else{
                return "noExist";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
