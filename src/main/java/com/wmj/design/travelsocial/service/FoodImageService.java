package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.information.FoodImage;

import java.util.List;

public interface FoodImageService {
    /**
     **通过美食id来查找美食图片
     **
     * @param foodId 美食id
     * @return List<FoodImage>
     */
    List<FoodImage> selectFoodImageByFoodId(int foodId);
}
