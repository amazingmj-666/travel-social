package com.wmj.design.travelsocial.controller.social;

import com.wmj.design.travelsocial.domain.social.Friend;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.FriendService;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.UsetInfoJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天功能Controller
 *
 * @author weimj
 * @date 2019/5/5
 */
@Controller
public class ChatController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    FriendService friendService;
    @GetMapping("/chat_view")
    public String friendList(HttpSession session, ModelMap mmap){
        int beAddId;
        int applicationId;
        int userId = userInfoService.selectIdByPhoneNumber(session);
        List<UserInfo> userList = new ArrayList();
        List<Friend> friendList = friendService.selectFriendList(userId);
        if (friendList.isEmpty()){
            return "/fail/no_friend";
        }else{
            for (int i =0;i<friendList.size();i++){
                if(friendList.get(i).getApplicationId() == userId ){
                    beAddId = friendList.get(i).getBeAddId();
                    UserInfo userInfo = userInfoService.selectUserInfoByID(beAddId);
                    UsetInfoJudge.userNameJudge(userInfo);
                    userList.add(userInfo);
                }else{
                    applicationId = friendList.get(i).getApplicationId();
                    UserInfo userInfo = userInfoService.selectUserInfoByID(applicationId);
                    UsetInfoJudge.userNameJudge(userInfo);
                    userList.add(userInfo);
                }
            }
            mmap.put("userList",userList);
            return "/social/chat/chat_view";
        }
    }
    @RequestMapping("/change_view")
    public String changeView(int userId,ModelMap mmap,HttpSession session){
        int beAddId;
        int applicationId;
        int id = userInfoService.selectIdByPhoneNumber(session);
        List<UserInfo> userList = new ArrayList();
        List<Friend> friendList = friendService.selectFriendList(id);
        if (friendList.isEmpty()){
            return "/fail/no_friend";
        }else{
            for (int i =0;i<friendList.size();i++){
                if(friendList.get(i).getApplicationId() == id ){
                    beAddId = friendList.get(i).getBeAddId();
                    UserInfo userInfo = userInfoService.selectUserInfoByID(beAddId);
                    UsetInfoJudge.userNameJudge(userInfo);
                    userList.add(userInfo);
                }else{
                    applicationId = friendList.get(i).getApplicationId();
                    UserInfo userInfo = userInfoService.selectUserInfoByID(applicationId);
                    UsetInfoJudge.userNameJudge(userInfo);
                    userList.add(userInfo);
                }
            }
            UserInfo userInfo1 = userInfoService.selectUserInfoByID(userId);
            UserInfo userInfo2 = userInfoService.selectUserInfoByID(id);
            UsetInfoJudge.userNameJudge(userInfo1);
            mmap.put("userList",userList);
            mmap.put("userInfo1",userInfo1);
            mmap.put("userInfo2",userInfo2);
            return "/social/chat/friend_chat_view";
        }
    }
}
