package com.wmj.design.travelsocial.domain.information;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Tips {
    /**
     * 主键
     */
    @Getter
    @Setter
    private int id;
    /**
     * 标题
     */
    @Getter
    @Setter
    private String title;
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
