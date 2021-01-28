package com.wmj.design.travelsocial.domain.information;


import lombok.Data;

import java.util.Date;

@Data
public class Food {
    /**
     * 主键
     */
    private int id;
    /**
     * 美食名称
     */
    private String name;
    /**
     * 美食描述
     */
    private String describes;
    /**
     * 美食价格
     */
    private String price;
    /**
     * 城市
     */
    private String city;
    /**
     * 具体地址
     */
    private String address;
    /**
     * 代表图片的路径
     */
    private String image;
    /**
     * 创建时间
     */
    private Date createTime;
}
