package com.wmj.design.travelsocial.dao;

import com.wmj.design.travelsocial.domain.information.Food;

import java.util.List;
/**
 * FoodMapper
 *
 * @author weimj
 * @date 2019/5/20
 */
public interface FoodMapper {
    /**
     **随机选取美食中的3条数据
     **
     *
     * @return List<Food>
     */
    List<Food> selectFoodByRandom();
    /**
     **通过城市来查找美食
     **
     * @param city 城市
     * @return List<Food>
     */
    List<Food> selectFoodListByCity(String city);
    /**
     **通过美食名称来查找美食
     **
     * @param name 景点名称
     * @return List<Food>
     */
    List<Food> selectFoodListByName(String name);
    /**
     **通过id来查找景点
     **
     * @param id 美景id
     * @return Scenic
     */
    Food selectFoodById(int id);
}
