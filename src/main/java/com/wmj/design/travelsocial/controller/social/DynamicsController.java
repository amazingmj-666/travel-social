package com.wmj.design.travelsocial.controller.social;

import com.wmj.design.travelsocial.domain.social.Dynamics;
import com.wmj.design.travelsocial.domain.social.DynamicsImage;
import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.framework.config.TravelConfig;
import com.wmj.design.travelsocial.framework.web.controller.BaseController;
import com.wmj.design.travelsocial.framework.web.domain.AjaxResult;
import com.wmj.design.travelsocial.service.DynamicsImageService;
import com.wmj.design.travelsocial.service.DynamicsService;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 动态Controller
 *
 * @author weimj
 * @date 2019/5/6
 */
@Slf4j
@Controller
public class DynamicsController {
    @Autowired
    DynamicsService dynamicsService;
    @Autowired
    DynamicsImageService dynamicsImageService;
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/release_dynamics")
    public String releaseDynamics(){
        return "/social/dynamics/release_dynamics";
    }

    @GetMapping("/my_dynamics")
    public String myDynamics(HttpSession session, ModelMap mmap){
        int userId = userInfoService.selectIdByPhoneNumber(session);
        int dynamicsId;
        try{
            List<Dynamics> dynamicsList = dynamicsService.selectDynamicsList(userId);
            if (dynamicsList.isEmpty()){
                return "/fail/no_dynamics";

            }else{
                UserInfo userInfo = userInfoService.selectUserInfoByID(userId);
                for (int i =0;i<dynamicsList.size();i++){
                    dynamicsId = dynamicsList.get(i).getId();
                    List<DynamicsImage> dynamicsImageList = dynamicsImageService.selectDynamicsImageList(dynamicsId);
                    dynamicsList.get(i).setImage(dynamicsImageList);
                    dynamicsList.get(i).setUserInfo(userInfo);
                }
                mmap.put("dynamicsList",dynamicsList);
                return "/social/dynamics/my_dynamics";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/fail/system_error";
        }
    }

    @RequestMapping("/upload_picture")
    @ResponseBody
    public String releaseDynamics(HttpSession session,MultipartFile file){
        int dynamicsId = userInfoService.selectIdByPhoneNumber(session);
        try{
            if (!file.isEmpty()){
                String uploadImg = FileUploadUtils.upload(TravelConfig.getUploadPath(),file);
                DynamicsImage dynamicsImage = new DynamicsImage();
                dynamicsImage.setImageRoute(uploadImg);
                dynamicsImage.setDynamicsId(dynamicsId*100);
                if(dynamicsImageService.insertDynamicsImage(dynamicsImage) > 0){
                    return "finish";
                }
            }
            return "1";
        }catch(Exception e){
            log.error("上传图像失败",e);
            return "1";
        }
    }

    @RequestMapping( "/add_dynamics")
    @ResponseBody
    public String addDynamics(HttpSession session,String dynamicsContent){
        int userId = userInfoService.selectIdByPhoneNumber(session);
        Dynamics dynamics = new Dynamics();
        DynamicsImage dynamicsImage = new DynamicsImage();
        try{
            dynamics.setContent(dynamicsContent);
            dynamics.setUserId(userId);
            if(dynamicsService.insertDynamics(dynamics) > 0){
                int dynamicsId = dynamics.getId();
                dynamicsImage.setDynamicsId(dynamicsId);
                if(dynamicsImageService.updateDynamicsImageByDynamicsId(dynamicsId,userId*100) > 0){
                    return "success";
                }
                return "error";
            }
            return "error";
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/delete_dynamics")
    @ResponseBody
    public String deleteDynamics(HttpSession session,int dynamicsId){
        try{
            //System.out.println(dynamicsId);
            if (dynamicsService.deleteDynamics(dynamicsId) > 0){
                if (dynamicsImageService.deleteDynamicsImage(dynamicsId) > 0){
                    return "success";
                }else{
                    return "error";
                }
            }else{
                return "error";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
