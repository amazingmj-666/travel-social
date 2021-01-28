package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.information.Tips;

import java.util.List;

public interface TipsService {
    /**
     **查找小贴士列表
     **
     * @return Tips
     */
    List<Tips> selectTipsList();
}
