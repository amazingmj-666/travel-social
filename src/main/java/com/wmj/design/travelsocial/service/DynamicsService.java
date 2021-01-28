package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.social.Dynamics;

import java.util.List;

public interface DynamicsService {
    /**
     **新增动态信息
     **
     * @param dynamics 动态对象
     * @return id
     */
    int insertDynamics(Dynamics dynamics);
    /**
     **查找动态列表
     **
     * @param userId 用户ID
     * @return Friend
     */
    List<Dynamics> selectDynamicsList(int userId);
    /**
     **删除动态信息
     **
     * @param id 动态id
     * @return id
     */
    int deleteDynamics(int id);
}
