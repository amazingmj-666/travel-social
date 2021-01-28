package com.wmj.design.travelsocial.controller.social;

import com.wmj.design.travelsocial.domain.social.Friend;
import com.wmj.design.travelsocial.domain.social.FriendApplication;
import com.wmj.design.travelsocial.service.FriendApplicationService;
import com.wmj.design.travelsocial.service.FriendService;
import com.wmj.design.travelsocial.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
/**
 * 好友表的操作
 *
 * @author weimj
 * @date 2019/5/3
 */
@Controller
public class AddFriendController {
    @Autowired
    DataSourceTransactionManager transactionManager;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    FriendApplicationService friendApplicationService;
    @Autowired
    FriendService friendService;
    @RequestMapping("/add_friend")
    @ResponseBody
    public String addFriend(int applicationId, HttpSession session){
        try{
            FriendApplication friendApplication = new FriendApplication();
            Friend friend = new Friend();
            String status = "1";
            int beAddId = userInfoService.selectIdByPhoneNumber(session);
            friendApplication.setApplicationId(applicationId);
            friendApplication.setBeAddId(beAddId);
            friendApplication.setStatus(status);
            if (friendApplicationService.updateFriendApplicationStatus(friendApplication) > 0){
                friend.setApplicationId(applicationId);
                friend.setBeAddId(beAddId);
                if (friendService.insertFriend(friend) >0){
                    return "agree";
                }else{
                    return "error";
                }
            }else{
                return "error";
            }
        }catch(Exception e){
            e.printStackTrace();
            /*transactionManager.rollback();*/
            return "error";
        }
    }
    @RequestMapping("/refuse_friend_application")
    @ResponseBody
    public String refuseFriendApplication(int applicationId,HttpSession session){
        try{
            FriendApplication friendApplication = new FriendApplication();
            String status = "2";
            int beAddId = userInfoService.selectIdByPhoneNumber(session);
            friendApplication.setApplicationId(applicationId);
            friendApplication.setBeAddId(beAddId);
            friendApplication.setStatus(status);
            if (friendApplicationService.updateFriendApplicationStatus(friendApplication) > 0){
                return "success";
            }else{
                return "error";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
