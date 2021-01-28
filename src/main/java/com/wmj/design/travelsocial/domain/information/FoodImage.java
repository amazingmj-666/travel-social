package com.wmj.design.travelsocial.domain.information;

import lombok.Data;

import java.util.Date;

@Data
public class FoodImage {
    /**
     * 主键
     */
    private int id;
    /**
     * 美食主键
     */
    private int foodId;
    /**
     * 拖路径
     */
    private String imageRoute;
    /**
     * 创建时间
     */
    private Date createTime;

}
