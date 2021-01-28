package com.wmj.design.travelsocial.utils;

import com.wmj.design.travelsocial.domain.social.Dynamics;

import java.util.Comparator;

public class SortUtils implements Comparator {
    @Override
    public int compare(Object o1, Object o2){
        Dynamics dynamics1 = (Dynamics)o1;
        Dynamics dynamics2 = (Dynamics)o2;
        int flag = dynamics2.getCreateTime().compareTo(dynamics1.getCreateTime());

        return flag;
    }
}
