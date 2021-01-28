package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.information.Custom;

import java.util.List;

public interface CustomService {
    /**
     **随机选取习俗中的4条数据
     **
     *
     * @return Custom
     */
    List<Custom> selectCustomByRandom();
    /**
     **通过国家来查找习俗
     **
     * @param custom 习俗
     * @return Custom
     */
    List<Custom> selectCustomListByCountry(Custom custom);
    /**
     **通过id来查找习俗
     **
     * @param custom 习俗
     * @return Custom
     */
    Custom selectCustomById(Custom custom);
}
