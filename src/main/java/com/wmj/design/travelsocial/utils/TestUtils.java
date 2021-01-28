package com.wmj.design.travelsocial.utils;
import org.springframework.util.DigestUtils;

public class TestUtils {
    public static void main(String args[]){
        String s = "Wmz12580";
        String md5Password = DigestUtils.md5DigestAsHex(s.getBytes());
        System.out.println(md5Password);
        Md5Utils md5Utils = new Md5Utils();
        System.out.println(Md5Utils.hash(s));
    }
}
