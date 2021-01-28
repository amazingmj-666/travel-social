package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.social.Friend;

import java.util.List;

public interface FriendService {
    /**
     **新增好友信息
     **
     * @param friend 好友对象
     * @return id
     */
    int insertFriend(Friend friend);
    /**
     **查找好友是否存在
     **
     * @param friend 好友对象
     * @return Friend
     */
    Friend selectFriendIsExist(Friend friend);
    /**
     **查找好友列表
     **
     * @param userId 用户ID
     * @return Friend
     */
    List<Friend> selectFriendList(int userId);
}
