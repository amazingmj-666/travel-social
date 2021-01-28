package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.FriendMapper;
import com.wmj.design.travelsocial.domain.social.Friend;
import com.wmj.design.travelsocial.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFriend(Friend friend){
        return friendMapper.insertFriend(friend);
    }
    @Override
    public Friend selectFriendIsExist(Friend friend){
        return friendMapper.selectFriendIsExist(friend);
    }
    @Override
    public List<Friend> selectFriendList(int userId){
        List<Friend> friends = friendMapper.selectFriendList(userId);
        return friends;
    }
}
