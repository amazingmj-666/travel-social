package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.DynamicsMapper;
import com.wmj.design.travelsocial.domain.social.Dynamics;
import com.wmj.design.travelsocial.service.DynamicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicsServiceImpl implements DynamicsService {
    @Autowired
    DynamicsMapper dynamicsMapper;
    @Override
    public int insertDynamics(Dynamics dynamics){
        return dynamicsMapper.insertDynamics(dynamics);
    }
    @Override
    public List<Dynamics> selectDynamicsList(int userId){
        List<Dynamics> dynamics = dynamicsMapper.selectDynamicsList(userId);
        return dynamics;
    }

    @Override
    public int deleteDynamics(int id){
        return dynamicsMapper.deleteDynamics(id);
    }
}
