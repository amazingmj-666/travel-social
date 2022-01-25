package com.wmj.design.travelsocial.controller.social;

import com.wmj.design.travelsocial.domain.social.Friend;
import com.wmj.design.travelsocial.domain.social.FriendApplication;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.FriendApplicationService;
import com.wmj.design.travelsocial.service.FriendService;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.UsetInfoJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加好友申请
 *
 * @author weimj
 * @date 2019/4/29
 */
@Controller
public class AddFriendApplicationController {

    @Autowired
    FriendApplicationService friendApplicationService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    FriendService friendService;

    @GetMapping("/add_friend")
    public  String addFriend(){
        return "social/add_friend";
    }

    @GetMapping("/friend_application")
    public String friendAoolication(HttpSession session, ModelMap mmap){
        UserInfo userInfo;
        List<UserInfo> userList = new ArrayList();
        int beAddId = userInfoService.selectIdByPhoneNumber(session);
        FriendApplication friendApplication = new FriendApplication();
        friendApplication.setBeAddId(beAddId);
        friendApplication.setStatus("0");
        List<FriendApplication> lists  = friendApplicationService.selectFriendApplicationList(friendApplication);
        if (lists.isEmpty()){
            return "fail/no_friend_application";
        }else{
            for(int i = 0;i<lists.size();i++){
                int applicationId = lists.get(i).getApplicationId();
                userInfo = userInfoService.selectUserInfoByID(applicationId);
                userInfo.setHobby(lists.get(i).getMessage());
                userInfo.setCreateTime(lists.get(i).getCreateTime());
                UsetInfoJudge.userInfoJudge(userInfo);
                userList.add(userInfo);
            }
            mmap.put("userList",userList);
            mmap.put("lists",lists);
            return "social/friendApplication/friend_application";
        }
    }

    @RequestMapping("/add_friend_application")
    @ResponseBody
    public String addFriend(int userId, HttpSession session, String message){
        try{
            int applicationId = userInfoService.selectIdByPhoneNumber(session);
            FriendApplication friendApplication = new FriendApplication();
            friendApplication.setApplicationId(applicationId);
            friendApplication.setBeAddId(userId);
            FriendApplication friendApplicationResult = friendApplicationService.selectFriendApplicationIsExist(friendApplication);
            if (ObjectUtils.isEmpty(friendApplicationResult)){
                friendApplication.setMessage(message);
                if(friendApplicationService.insertFriendApplication(friendApplication) >0){
                    return "success";
                }else{
                    return "error";
                }
            }else{
                if (friendApplicationResult.getStatus().equals("0")){
                    return "applicationIsExist";
                }else if (friendApplicationResult.getStatus().equals("1")){
                    return "friendIsExist";
                }else{
                    return "error";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
