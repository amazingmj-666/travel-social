package com.wmj.design.travelsocial.domain.information;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class ScenicImage {
    /**
     * 主键
     */
    @Getter
    @Setter
    private int id;
    /**
     * 景点主键
     */
    @Getter
    @Setter
    private int scenicId;
    /**
     * 图片路径
     */
    @Getter
    @Setter
    private String imageRoute;
    /**
     * 主键
     */
    @Getter
    @Setter
    private Date createTime;
}
