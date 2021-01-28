package com.wmj.design.travelsocial.domain.social;

import com.wmj.design.travelsocial.domain.user.UserInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Dynamics {
    /**
     * 动态表主键
     *
     */
    private int id;
    /**
     * 用户ID
     *
     */
    private int userId;
    /**
     * 动态内容
     *
     */
    private String content;
    /**
     * 创建时间
     *
     */
    private Date createTime;
    /**
     *图片路径
     *
     */
    private List<DynamicsImage> image;

    private UserInfo userInfo;
}
