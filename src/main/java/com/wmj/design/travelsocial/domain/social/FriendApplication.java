package com.wmj.design.travelsocial.domain.social;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
public class FriendApplication {
    /**
     * 主键
     *
     */
    private int id;
    /**
     * 申请者的id
     */

    private int applicationId;
    /**
     * 被添加者的id
     */

    private int beAddId;
    /**
     * 验证信息
     */

    private String message;
    /**
     * 申请创建时间
     */
    private Date createTime;
    /**
     * 申请的状态
     */
    private String status;
}
