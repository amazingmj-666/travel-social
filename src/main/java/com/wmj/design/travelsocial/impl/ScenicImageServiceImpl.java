package com.wmj.design.travelsocial.impl;

import com.wmj.design.travelsocial.dao.ScenicImageMapper;
import com.wmj.design.travelsocial.domain.information.ScenicImage;
import com.wmj.design.travelsocial.service.ScenicImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ScenicImageServiceImpl 美景图片
 *
 * @author weimj
 * @date 2019/5/19
 */
@Service
public class ScenicImageServiceImpl implements ScenicImageService {
    @Autowired
    ScenicImageMapper scenicImageMapper;
    @Override
    public List<ScenicImage> selectScenicImageByScenicId(int scenicId){
        List<ScenicImage> scenicImageList = scenicImageMapper.selectScenicImageByScenicId(scenicId);
        return scenicImageList;
    }
}
