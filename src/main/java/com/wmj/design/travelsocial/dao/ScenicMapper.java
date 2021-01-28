package com.wmj.design.travelsocial.dao;

import com.wmj.design.travelsocial.domain.information.Scenic;

import java.util.List;

public interface ScenicMapper {
    /**
     **随机选取景点中的3条数据
     **
     *
     * @return List<Scenic>
     */
    List<Scenic> selectScenicByRandom();
    /**
     **通过城市来查找景点
     **
     * @param city 城市
     * @return List<Scenic>
     */
    List<Scenic> selectScenicListByCity(String city);
    /**
     **通过景点名称来查找景点
     **
     * @param scenicName 景点名称
     * @return List<Scenic>
     */
    List<Scenic> selectScenicListByScenicName(String scenicName);
    /**
     **通过id来查找景点
     **
     * @param id 美景id
     * @return Scenic
     */
    Scenic selectScenicById(int id);
}
