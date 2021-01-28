package com.wmj.design.travelsocial.dao;

import com.wmj.design.travelsocial.domain.social.Friend;
import com.wmj.design.travelsocial.domain.social.FriendApplication;

import java.util.List;

public interface FriendApplicationMapper {
    /**
            **新增好友申请信息
     **
     * @param friendApplication 好友申请
     * @return id
     */
    int insertFriendApplication(FriendApplication friendApplication);

    /**
     * 通过被添加者的id进行申请数目的计算
     *
     * @param beAddId 被添加者id
     * @return id
     */
    int selectFriendApplicationCountByBeAddId(int beAddId);
    /**
     * 查询好友申请列表
     *
     * @param  friendApplication 被添加者id
     * @return 申请添加好友集合
     */
    List<FriendApplication> selectFriendApplicationList(FriendApplication friendApplication);
    /**
     * 更新好友申请表的状态(同意改为1，拒绝改为2)
     *
     * @param friendApplication
     * @return int
     */
    int updateFriendApplicationStatus(FriendApplication friendApplication);
    /**
     **查找好友申请是否存在
     **
     * @param friendApplication 好友申请
     * @return Friend
     */
    FriendApplication selectFriendApplicationIsExist(FriendApplication friendApplication);
}
