package com.wmj.design.travelsocial.dao;

import com.wmj.design.travelsocial.domain.information.ScenicImage;

import java.util.List;

public interface ScenicImageMapper {
    /**
     **通过景点id来查找景点图片
     **
     * @param scenicId 美景id
     * @return List<ScenicImage>
     */
    List<ScenicImage> selectScenicImageByScenicId(int scenicId);
}
