package com.wmj.design.travelsocial.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    /**
     * 用户表主键
     *
     */
    private int id;
    /**
     * 密码
     *
     */
    private String password;
    /**
     * 手机号
     *
     */
    private String phonenumber;
    /**
     * 城市
     *
     */
    private String city;
    /**
     * 用户昵称
     *
     */
    private String userName;
    /**
     * 电子邮箱
     *
     */
    private String email;
    /**
     * 生日
     *
     */
    private String birthday;
    /**
     * 爱好
     *
     */
    private String hobby;
    /**
     * 性别
     *
     */
    private String sex;
    /**
     * 创建时间
     *
     */
    private Date createTime;
    /**
     * 用户状态
     *
     */
    private String status;
    /**
     * 头像地址
     *
     */
    private String avatar;
}
