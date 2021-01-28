package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.FriendApplicationMapper;
import com.wmj.design.travelsocial.domain.social.FriendApplication;
import com.wmj.design.travelsocial.service.FriendApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 好友申请表的操作
 *
 * @author weimj
 * @date 2019/5/1
 */
@Service
public class FriendApplicationServiceImpl implements FriendApplicationService {
    @Autowired
    FriendApplicationMapper friendApplicationMapper;
    @Override
    public int insertFriendApplication(FriendApplication friendApplication){
        return  friendApplicationMapper.insertFriendApplication(friendApplication);

    }
    @Override
    public int selectFriendApplicationCountByBeAddId(int beAddId){
        return  friendApplicationMapper.selectFriendApplicationCountByBeAddId(beAddId);
    }

    @Override
   public List<FriendApplication> selectFriendApplicationList(FriendApplication friendApplication){
        List<FriendApplication> friendApplications = friendApplicationMapper.selectFriendApplicationList(friendApplication);
        return friendApplications;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateFriendApplicationStatus(FriendApplication friendApplication){
        return friendApplicationMapper.updateFriendApplicationStatus(friendApplication);
    }
    @Override
    public FriendApplication selectFriendApplicationIsExist(FriendApplication friendApplication){
        return friendApplicationMapper.selectFriendApplicationIsExist(friendApplication);
    }
}
