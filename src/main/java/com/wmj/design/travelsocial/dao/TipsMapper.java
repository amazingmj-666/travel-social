package com.wmj.design.travelsocial.dao;

import com.wmj.design.travelsocial.domain.information.Tips;

import java.util.List;

public interface TipsMapper {
    /**
     **查找小贴士列表
     **
     * @return Tips
     */
    List<Tips> selectTipsList();
}
