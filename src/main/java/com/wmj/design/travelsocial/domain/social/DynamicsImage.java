package com.wmj.design.travelsocial.domain.social;

import lombok.Data;

@Data
public class DynamicsImage {
    /**
     * 动态图片表主键
     *
     */
    private int id;
    /**
     * 动态id
     *
     */
    private int dynamicsId;
    /**
     * 图片路径
     *
     */
    private String imageRoute;
}
