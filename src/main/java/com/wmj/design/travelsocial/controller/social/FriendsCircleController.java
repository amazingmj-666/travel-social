package com.wmj.design.travelsocial.controller.social;

import com.wmj.design.travelsocial.dao.DynamicsImageMapper;
import com.wmj.design.travelsocial.dao.DynamicsMapper;
import com.wmj.design.travelsocial.dao.FriendMapper;
import com.wmj.design.travelsocial.dao.UserInfoMapper;
import com.wmj.design.travelsocial.domain.social.Dynamics;
import com.wmj.design.travelsocial.domain.social.DynamicsImage;
import com.wmj.design.travelsocial.domain.social.Friend;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.DynamicsImageService;
import com.wmj.design.travelsocial.service.DynamicsService;
import com.wmj.design.travelsocial.service.FriendService;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.SortUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class FriendsCircleController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    FriendService friendService;
    @Autowired
    DynamicsService dynamicsService;
    @Autowired
    DynamicsImageService dynamicsImageService;
    @GetMapping("/friends_circle")
    public String friendsCircle(HttpSession session, ModelMap mmap){
        int id = userInfoService.selectIdByPhoneNumber(session);
        int friendId1,friendId2;
        SortUtils sortUtils = new SortUtils();
        //通过id查找好友表
        List<Friend> friendList = friendService.selectFriendList(id);
        List<Dynamics> dynamicsList = new ArrayList();
        List<Dynamics> dynamicsAllList = new ArrayList<Dynamics>();
        List<DynamicsImage> dynamicsImageList;
        try{
            if(friendList.isEmpty()){
                return "/fail/no_friend";
            }else{
                for (int i =0;i<friendList.size();i++){
                    if(friendList.get(i).getApplicationId() == id ){
                        friendId1 = friendList.get(i).getBeAddId();
                        UserInfo userInfo = userInfoService.selectUserInfoByID(friendId1);
                        dynamicsList = dynamicsService.selectDynamicsList(friendId1);

                        for (int j=0;j<dynamicsList.size();j++){
                            int dynamicsId = dynamicsList.get(j).getId();
                            dynamicsImageList = dynamicsImageService.selectDynamicsImageList(dynamicsId);
                            dynamicsList.get(j).setImage(dynamicsImageList);
                            dynamicsList.get(j).setUserInfo(userInfo);
                            dynamicsAllList.add(dynamicsList.get(j));
                        }

                    }else{
                        friendId2 = friendList.get(i).getApplicationId();
                        UserInfo userInfo = userInfoService.selectUserInfoByID(friendId2);
                        dynamicsList = dynamicsService.selectDynamicsList(friendId2);
                        for (int k=0;k<dynamicsList.size();k++){
                            int dynamicsId = dynamicsList.get(k).getId();
                            dynamicsImageList = dynamicsImageService.selectDynamicsImageList(dynamicsId);
                            dynamicsList.get(k).setImage(dynamicsImageList);
                            dynamicsList.get(k).setUserInfo(userInfo);
                            dynamicsAllList.add(dynamicsList.get(k));
                        }
                    }
                }
                //按照动态发布时间进行降序排序（最新动态显示在最前面）
                Collections.sort(dynamicsAllList,sortUtils);
                mmap.put("dynamicsAllList",dynamicsAllList);
                return "/social/friendscircle/friends_circle";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }

}
