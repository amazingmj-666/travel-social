package com.wmj.design.travelsocial.controller.social;

import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.UsetInfoJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;

@Controller
public class SearchUserController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/search_user")
    public String searchUser(){
        return "/social/search_user";
    }

    @RequestMapping("/search_user")
    public String searchUser(String searchContent, ModelMap mmap){
        try{
            //先把输入内容当做手机号进行查找用户
            UserInfo ui1 =  userInfoService.findByPhoneNumber(searchContent);
            if (StringUtils.isEmpty(ui1)){
                //再把输入内容当做昵称进行查找用户
                UserInfo ui2 = userInfoService.selectUserInfoByUserName(searchContent);
                if (StringUtils.isEmpty(ui2)){
                    //两次都没查找，返回到“查无此人”的界面
                    return "/fail/search_null";
                }else{
                    UsetInfoJudge.userInfoJudge(ui2);
                    //把用户信息传递到前端进行显示
                    mmap.put("userInfo",ui2);
                }
            }else{
                UsetInfoJudge.userInfoJudge(ui1);
                mmap.put("userInfo",ui1);
            }
            //跳转到查找结果页面，进行用户信息显示
            return "/social/search_user_result";
        }catch(Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }
}
