package com.wmj.design.travelsocial.controller.validatecode;

import com.alibaba.fastjson.JSONObject;
import com.wmj.design.travelsocial.utils.KeyUtil;
import com.wmj.design.travelsocial.utils.RedisUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import org.omg.CORBA.ObjectHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 发送短信验证码(注册)
 *
 * @author weimj
 * @date 2019/4/10
 */
@Controller
public class SendSmsController {
    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(HttpServletRequest request,String phonenumber){
        try{
            String msg = null;
            //生成六位验证码
            String validateCode = String.valueOf(new Random().nextInt(899999)+100000);
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com","101180","34d1f6bc-a283-4196-adcc-84e24b3bea23");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("number", phonenumber);
            paramMap.put("templateId", "3473");
            String[] templateParams = new String[2];
            templateParams[0] = validateCode;
            templateParams[1] = "5";
            paramMap.put("templateParams", templateParams);
            String result = client.send(paramMap);
            JSONObject json = JSONObject.parseObject(result);
            if(json.getIntValue("code") != 0){
                System.out.println("发送失败");
                msg = "sendfail";
                return msg;
            }
            //将验证码存到redis中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            Map<String, Object> code = new HashMap<>();
            code.put("validateCode", validateCode);
            code.put("createTime", System.currentTimeMillis());
            String smsCodeKey = KeyUtil.getSmsCode(phonenumber);
            RedisUtil.set(smsCodeKey, code);
            request.getSession().setAttribute("validateCode",json);
            msg = "sendsuccess";
            System.out.println("验证码为" +  validateCode);
            return msg;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
