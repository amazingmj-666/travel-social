package com.wmj.design.travelsocial.domain.information;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Scenic {
    /**
     * 主键
     */
    @Getter
    @Setter
    private int id;
    /**
     * 景点名称
     */
    @Getter
    @Setter
    private String scenicName;
    /**
     * 景点介绍
     */
    @Getter
    @Setter
    private String scenicIntroduce;
    /**
     * 票价
     */
    @Getter
    @Setter
    private String ticketPrice;
    /**
     * 城市
     */
    @Getter
    @Setter
    private String city;
    /**
     * 具体地址
     */
    @Getter
    @Setter
    private String address;
    /**
     * 开放时间
     */
    @Getter
    @Setter
    private String openTime;
    /**
     * 标志性图片
     */
    @Getter
    @Setter
    private String image;
    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createTime;

}
