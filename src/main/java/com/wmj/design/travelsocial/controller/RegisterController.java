package com.wmj.design.travelsocial.controller;

import com.alibaba.fastjson.JSONObject;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.KeyUtil;
import com.wmj.design.travelsocial.utils.Md5Utils;
import com.wmj.design.travelsocial.utils.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 注册controller
 *
 * @author weimj
 * @date 2019/3/28
 */

@Controller
public class RegisterController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping("checkPhoneNumber")
    @ResponseBody
    public boolean checkPhoneNumber(String phonenumber){
        UserInfo userInfo = userInfoService.findByPhoneNumber(phonenumber);
        if (StringUtils.isEmpty(userInfo)){
            return true;
        }
        return false;
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request, @Param("phonenumber")String phonenumber,@Param("password") String password, String validateCode){

        String codeKey = KeyUtil.getSmsCode(phonenumber);
        Map<String, Object> json = (Map<String, Object>) RedisUtil.get(codeKey);
        //JSONObject json = (JSONObject)request.getSession().getAttribute("validateCode");
        String msg = null;
        try{
            //没有发送验证码，需要先发送验证码
            if (json == null) {
                msg = "sendFirst";
                return msg;
            } else {
                String code = json.get("validateCode").toString();
                if (!code.equals(validateCode)) {
                    msg = "errorcode";
                    return msg;
                }
            }
            if(StringUtils.isEmpty(validateCode)){
                System.out.println("验证码为空");
            }else if((System.currentTimeMillis() - Long.parseLong(json.get("createTime").toString())) > 1000 * 60 * 5){
                msg = "outtime";
                return msg;
            }else{
                //将用户信息存入数据库
                try{
                    UserInfo userInfo = new UserInfo();
                    if (userInfoService.findByPhoneNumber(phonenumber) == null){
                        userInfo.setPhonenumber(phonenumber);
                        userInfo.setPassword(Md5Utils.hash(password));
                        userInfoService.insertUserInfo(userInfo);
                        return "success";
                    }else{
                        return "exist";
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    return "fail";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }
}
