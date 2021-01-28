package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.TipsMapper;
import com.wmj.design.travelsocial.domain.information.Tips;
import com.wmj.design.travelsocial.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * TipsServiceImpl
 *
 * @author weimj
 * @date 2019/5/11
 */
@Service
public class TipsServiceImpl implements TipsService {
    @Autowired
    TipsMapper tipsMapper;
    @Override
    public List<Tips> selectTipsList(){
        List<Tips> tipsList = tipsMapper.selectTipsList();
        return tipsList;
    }
}
