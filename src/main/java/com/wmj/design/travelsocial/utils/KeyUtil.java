package com.wmj.design.travelsocial.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author wmj
 * @date 2021/1/26 17:49
 * @Description
 */
@Component
@Configuration
public class KeyUtil {

    private static String loginPre;
    private static String smsCodePre;

    // 登陆成功后存入redis的key
    public static String getLoginSuccessRedisKey(String phone) {
        return loginPre + phone.trim();
    }

    // 拼凑sessionId
    public static String getSessionId(String phone, String uuid) {
        return phone + "-" + uuid;
    }

    // 拼凑注册验证码的key
    public static String getSmsCode(String phone) {
        return smsCodePre + phone;
    }

    @Value("${commonStr.login.pre}")
    public void setLoginPre(String loginPre) {
        KeyUtil.loginPre = loginPre;
    }

    @Value("${commonStr.smsCode.pre}")
    public void setSmsCodePre(String smsCodePre) {
        KeyUtil.smsCodePre = smsCodePre;
    }
}
