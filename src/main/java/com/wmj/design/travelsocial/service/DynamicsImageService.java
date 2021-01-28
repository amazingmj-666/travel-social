package com.wmj.design.travelsocial.service;

import com.wmj.design.travelsocial.domain.social.DynamicsImage;

import java.util.List;

public interface DynamicsImageService {
    /**
     **新增动态图片信息
     **
     * @param dynamicsImage 动态图片对象
     * @return id
     */
    int insertDynamicsImage(DynamicsImage dynamicsImage);
    /**
     * 根据dynamicsId更新动态图片表
     *
     * @param dynamicsId 动态表Id
     * @return 用户信息
     */
    int updateDynamicsImageByDynamicsId(int dynamicsId,int userId);
    /**
     **查找动态图片列表
     **
     * @param dynamicsId 动态ID
     * @return DynamicsImage
     */
    List<DynamicsImage> selectDynamicsImageList(int dynamicsId);
    /**
     **删除动态图片
     **
     * @param dynamicsId 动态表id
     * @return id
     */
    int deleteDynamicsImage(int dynamicsId);
}
