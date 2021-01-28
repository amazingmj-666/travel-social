package com.wmj.design.travelsocial.utils;

import com.wmj.design.travelsocial.domain.user.UserInfo;

public class UsetInfoJudge {
    public static void userInfoJudge(UserInfo userInfo){
        if (userInfo.getCity() == null){
            userInfo.setCity("未完善");
        }
        if(userInfo.getUserName() == null){
            userInfo.setUserName("未完善");
        }
        if(userInfo.getEmail() == null){
            userInfo.setEmail("未完善");
        }
        if (userInfo.getBirthday() == null ){
            userInfo.setBirthday("未完善");
        }
        if (userInfo.getHobby() == null || userInfo.getHobby().isEmpty()){
            userInfo.setHobby("该用户很懒，没有填写...");
        }
        if (userInfo.getSex() == null){
            userInfo.setSex("未完善");
        }else{
            if (userInfo.getSex().equals("1")){
                userInfo.setSex("男");
            }else{
                userInfo.setSex("女");
            }
        }
    }
    public static void userNameJudge(UserInfo userInfo){
        if (userInfo.getUserName() == null){
            userInfo.setUserName(userInfo.getPhonenumber());
        }
    }
}
