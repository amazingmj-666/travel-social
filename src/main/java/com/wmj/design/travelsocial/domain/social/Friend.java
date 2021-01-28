package com.wmj.design.travelsocial.domain.social;

import lombok.Data;

import java.util.Date;

@Data
public class Friend {
    /**
     * 主键
     *
     */
    private int id;
    /**
     * 申请者id
     *
     */
    private int applicationId;
    /**
     * 被添加者id
     *
     */
    private int beAddId;
    /**
     * 添加时间
     *
     */
    private Date createTime;



}
