package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.FoodImageMapper;
import com.wmj.design.travelsocial.domain.information.FoodImage;
import com.wmj.design.travelsocial.service.FoodImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodImageServiceImpl implements FoodImageService {
    @Autowired
    FoodImageMapper foodImageMapper;
    @Override
    public List<FoodImage> selectFoodImageByFoodId(int foodId){
        List<FoodImage> foodImageList = foodImageMapper.selectFoodImageByFoodId(foodId);
        return foodImageList;
    }
}
