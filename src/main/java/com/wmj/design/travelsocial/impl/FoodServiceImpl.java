package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.FoodMapper;
import com.wmj.design.travelsocial.domain.information.Food;
import com.wmj.design.travelsocial.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * FoodServiceImpl
 *
 * @author weimj
 * @date 2019/5/20
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodMapper foodMapper;
    @Override
    public List<Food> selectFoodByRandom(){
        List<Food> foodList = foodMapper.selectFoodByRandom();
        return foodList;
    }
    @Override
    public List<Food> selectFoodListByCity(String city){
        List<Food> foodList = foodMapper.selectFoodListByCity(city);
        return foodList;
    }
    @Override
    public List<Food> selectFoodListByName(String name){
        List<Food> foodList = foodMapper.selectFoodListByName(name);
        return foodList;
    }
    @Override
    public Food selectFoodById(int id){
        return foodMapper.selectFoodById(id);
    }
}
