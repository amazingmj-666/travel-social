package com.wmj.design.travelsocial.controller.menu;

import com.wmj.design.travelsocial.domain.user.UserInfo;
import com.wmj.design.travelsocial.framework.config.TravelConfig;
import com.wmj.design.travelsocial.framework.web.controller.BaseController;
import com.wmj.design.travelsocial.framework.web.domain.AjaxResult;
import com.wmj.design.travelsocial.service.UserInfoService;
import com.wmj.design.travelsocial.utils.FileUploadUtils;
import com.wmj.design.travelsocial.utils.Md5Utils;
import com.wmj.design.travelsocial.utils.UsetInfoJudge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class ProfileController extends BaseController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/resetPwd")
    public String resetPwd(HttpSession session,ModelMap mmap){
        int id = userInfoService.selectIdByPhoneNumber(session);
        UserInfo userInfo = userInfoService.selectUserInfoByID(id);
        mmap.put("userInfo",userInfo);
        return "menu/profile/resetPwd";

    }
    @RequestMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(HttpSession session,String newPassword){
        try{
            int id = userInfoService.selectIdByPhoneNumber(session);
            UserInfo userInfo = userInfoService.selectUserInfoByID(id);
            userInfo.setPassword(Md5Utils.hash(newPassword));
            if(userInfoService.updateUserInfo(userInfo) > 0){
                return success();
            }else{
                return error();
            }
        }catch (Exception e){
            e.printStackTrace();
            return error();
        }
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(HttpSession session,String oldPassword){
        String phonenumber = (String) session.getAttribute("phonenumber");
        UserInfo userInfo = userInfoService.findByPhoneNumber(phonenumber);
        if(userInfo.getPassword().equals(Md5Utils.hash(oldPassword))){
            return true;
        }
        return false;
    }
    @GetMapping("checkUserName")
    @ResponseBody
    public boolean checkUserName(String userName,HttpSession session){
        int userId = userInfoService.selectIdByPhoneNumber(session);
        UserInfo userInfo = userInfoService.selectUserInfoByUserName(userName);
        if (!StringUtils.isEmpty(userInfo) && userInfo.getId() != userId){
            return false;
        }
        return true;
    }

    @GetMapping("checkEmail")
    @ResponseBody
    public boolean checkEmail(String email,HttpSession session){
        int userId = userInfoService.selectIdByPhoneNumber(session);
        UserInfo userInfo = userInfoService.selectUserInfoByEmail(email);
        if (!StringUtils.isEmpty(userInfo) && userInfo.getId() != userId){
            return false;
        }
        return true;
    }

    @GetMapping("/editInfo")
    public String editInfo(HttpSession session,ModelMap mmap){
        int userId = userInfoService.selectIdByPhoneNumber(session);
        UserInfo userInfo = userInfoService.selectUserInfoByID(userId);
        mmap.put("userInfo",userInfo);
        return "menu/profile/edit_info";

    }

    @RequestMapping("/editInfo")
    @ResponseBody
    public AjaxResult editInfo(HttpSession session,String city,String userName,String email,String birthday, String hobby, String sex){
        try{
            int id = userInfoService.selectIdByPhoneNumber(session);
            UserInfo userInfo = userInfoService.selectUserInfoByID(id);
            userInfo.setCity(city);
            userInfo.setUserName(userName);
            userInfo.setEmail(email);
            userInfo.setBirthday(birthday);
            userInfo.setHobby(hobby);
            userInfo.setSex(sex);
            if(userInfoService.updateUserInfo(userInfo) > 0){
                return success();
            }else{
                return error();
            }
        }catch (Exception e){
            e.printStackTrace();
            return error();
        }
    }

    @GetMapping("/avatar")
    public String avatar(HttpSession session,ModelMap mmap){
        int id = userInfoService.selectIdByPhoneNumber(session);
        UserInfo userInfo = userInfoService.selectUserInfoByID(id);
        mmap.put("userInfo",userInfo);
        return "menu/profile/avatar";
    }

    @RequestMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(HttpSession session,@RequestParam("avatarfile")MultipartFile file){
        int id = userInfoService.selectIdByPhoneNumber(session);
        UserInfo userInfo = userInfoService.selectUserInfoByID(id);
        try{
            if (!file.isEmpty()){
                String avatar = FileUploadUtils.upload(TravelConfig.getAvatarPath(),file);
                userInfo.setAvatar(avatar);
                if(userInfoService.updateUserInfo(userInfo) > 0){
                    return success();
                }
            }
            return error();
        }catch(Exception e){
            log.error("修改头像失败",e);
            return error(e.getMessage());
        }
    }

    @RequestMapping("/profile")
    public String profile(HttpSession session,ModelMap mmap){
       int id = userInfoService.selectIdByPhoneNumber(session);
       UserInfo userInfo = userInfoService.selectUserInfoByID(id);
       UsetInfoJudge.userInfoJudge(userInfo);
       mmap.put("userInfo",userInfo);
       return "menu/profile";
    }
}
