package com.wmj.design.travelsocial.domain.information;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Custom {
    /**
     * 主键
     */
    @Getter
    @Setter
    private int id;
    /**
     * 国家
     */
    @Getter
    @Setter
    private String country;
    /**
     * 标题
     */
    @Getter
    @Setter
    private String title;
    /**
     * 概要
     */
    @Getter
    @Setter
    private String outline;
    /**
     * 内容
     */
    @Getter
    @Setter
    private String content;
    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createTime;
}
