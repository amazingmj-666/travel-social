package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public interface UserInfoService {
    /**
     * 保存用户信息
     * @param userInfo 用户信息
     */
    int insertUserInfo(UserInfo userInfo);
    /**
     * 新增添加的用户信息
     *
     * @param userInfo 用户信息
     * @return id
     */
    int insertAddUserInfo(UserInfo userInfo);

    /**
     * 根据手机号查询用户
     * @param phoneNumber 手机号
     * @return 用户信息
     */
    UserInfo findByPhoneNumber(String phoneNumber);
    /**
     * 根据手机号和密码查找用户信息
     *
     * @param userInfo 用户信息
     * @return 用户信息
     */
    UserInfo findByUserInfo(UserInfo userInfo);
    /**
     * 根据id新增用户信息
     *
     * @param userInfo 用户信息
     * @return 用户信息
     */
    int updateUserInfo(UserInfo userInfo);
    /**
     * 根据id查找用户信息
     *
     * @param id 用户id
     * @return UserInfo
     */
    UserInfo selectUserInfoByID(int id);

    /**
     * 根据userName查找用户信息
     *
     * @param userName 用户昵称
     * @return UserInfo
     */
    UserInfo selectUserInfoByUserName(String userName);

    /**
     * 根据email查找用户信息
     *
     * @param email 邮箱
     * @return UserInfo
     */
    UserInfo selectUserInfoByEmail(String email);

    int selectIdByPhoneNumber(HttpSession session);


}
