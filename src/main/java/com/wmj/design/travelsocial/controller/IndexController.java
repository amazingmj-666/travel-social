package com.wmj.design.travelsocial.controller;

import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.FriendApplicationService;
import com.wmj.design.travelsocial.service.UserInfoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 首页
 *
 * @author weimj
 * @date 2019/3/28
 */
@Controller
public class IndexController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    FriendApplicationService friendApplicationService;
    @GetMapping("/index")
    public String index(HttpSession session, ModelMap mmap){
        if (session.getAttribute("phonenumber") == null){
            return "login";
        }else{
            String phonenumber = (String) session.getAttribute("phonenumber");
            String status = "0";
            int beAddId = userInfoService.selectIdByPhoneNumber(session);
            int count = friendApplicationService.selectFriendApplicationCountByBeAddId(beAddId);
            UserInfo userInfo = userInfoService.selectUserInfoByID(beAddId);
            String userName = userInfo.getUserName();
            if (StringUtils.isEmpty(userName)){
                userInfo.setUserName("未完善");
            }
            mmap.put("userInfo",userInfo);
            mmap.put("count",count);
            return "index";
        }
    }
    @GetMapping("/index_v1")
    public String index_v1(){
        return "index_v1";
    }
}
