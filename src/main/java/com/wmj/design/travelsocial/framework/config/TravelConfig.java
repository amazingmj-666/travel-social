package com.wmj.design.travelsocial.framework.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author weimj
 */
@Component
@ConfigurationProperties(prefix = "travel")
public class TravelConfig {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String version;
    @Getter
    @Setter
    private String copyrightYear;
    private static String profile;
    private static boolean addressEnabled;

    public void setProfile(String profile) {
        TravelConfig.profile = profile;
    }

    public static String getProfile() {
        return profile;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        TravelConfig.addressEnabled = addressEnabled;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    public static String getDownloadPath() {
        return profile + "download/";
    }

    public static String getUploadPath() {
        return profile + "upload/";
    }
}
