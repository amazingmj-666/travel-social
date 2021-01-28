package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.UserInfoMapper;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int insertUserInfo(UserInfo userInfo){
        return userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public UserInfo findByPhoneNumber(String phoneNumber){
        return userInfoMapper.findByPhoneNumber(phoneNumber);
    }

    @Override
    public UserInfo findByUserInfo(UserInfo userInfo){
        return userInfoMapper.findByUserInfo(userInfo);
    }

    @Override
    public int insertAddUserInfo(UserInfo userInfo){
        return userInfoMapper.insertAddUserInfo(userInfo);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo){
        return userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public UserInfo selectUserInfoByID(int id){
        return userInfoMapper.selectUserInfoByID(id);
    }

    @Override
    public UserInfo selectUserInfoByUserName(String userName){
        return userInfoMapper.selectUserInfoByUserName(userName);
    }

    @Override
    public UserInfo selectUserInfoByEmail(String email){
        return userInfoMapper.selectUserInfoByEmail(email);
    }

    @Override
    public int selectIdByPhoneNumber(HttpSession session){
        String phonenumber = (String) session.getAttribute("phonenumber");
        return userInfoMapper.findByPhoneNumber(phonenumber).getId();
    }
}
