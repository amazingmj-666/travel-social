package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.ScenicMapper;
import com.wmj.design.travelsocial.domain.information.Scenic;
import com.wmj.design.travelsocial.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicServiceImpl implements ScenicService {
    @Autowired
    ScenicMapper scenicMapper;
    @Override
    public List<Scenic> selectScenicByRandom(){
        List<Scenic> scenicList = scenicMapper.selectScenicByRandom();
        return scenicList;
    }

    @Override
    public List<Scenic> selectScenicListByCity(String city){
        List<Scenic> scenicList = scenicMapper.selectScenicListByCity(city);
        return scenicList;
    }

    @Override
    public List<Scenic> selectScenicListByScenicName(String scenicName){
        List<Scenic> scenicList = scenicMapper.selectScenicListByScenicName(scenicName);
        return scenicList;
    }

    @Override
    public Scenic selectScenicById(int id){
        return scenicMapper.selectScenicById(id);
    }
}
