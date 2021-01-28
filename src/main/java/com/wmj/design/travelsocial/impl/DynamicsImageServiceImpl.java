package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.DynamicsImageMapper;
import com.wmj.design.travelsocial.domain.social.DynamicsImage;
import com.wmj.design.travelsocial.service.DynamicsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicsImageServiceImpl implements DynamicsImageService {
    @Autowired
    DynamicsImageMapper dynamicsImageMapper;
    @Override
    public int insertDynamicsImage(DynamicsImage dynamicsImage){
        return dynamicsImageMapper.insertDynamicsImage(dynamicsImage);

    }
    @Override
    public int updateDynamicsImageByDynamicsId(int dynamicsId,int userId){
        return dynamicsImageMapper.updateDynamicsImageByDynamicsId(dynamicsId,userId);
    }
    @Override
    public List<DynamicsImage> selectDynamicsImageList(int dynamicsId){
        List<DynamicsImage> dynamicsImages = dynamicsImageMapper.selectDynamicsImageList(dynamicsId);
        return dynamicsImages;
    }

    @Override
    public int deleteDynamicsImage(int dynamicsId){
        return dynamicsImageMapper.deleteDynamicsImage(dynamicsId);
    }

}
