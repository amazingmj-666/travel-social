package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.CustomMapper;
import com.wmj.design.travelsocial.domain.information.Custom;
import com.wmj.design.travelsocial.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    CustomMapper customMapper;
    @Override
    public List<Custom> selectCustomByRandom(){
        return customMapper.selectCustomByRandom();
    }

    @Override
    public List<Custom> selectCustomListByCountry(Custom custom){
        return customMapper.selectCustomListByCountry(custom);
    }
    @Override
    public Custom selectCustomById(Custom custom){
        return customMapper.selectCustomById(custom);
    }
}
