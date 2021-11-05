package com.wmj.design.travelsocial.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wmj
 * @date 2021/9/23 11:27
 * @Description
 */
public class hard {

    @Test
    public void testArrangeActivity() {
        int[] start = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] end = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        List<Integer> results = arrangeActivity(start, end);
        for (int i = 0; i < results.size(); i++) {
            int index = results.get(i);
            System.out.println("开始时间:" + start[index] + ",结束时间:" + end[index]);
        }
    }

    public List<Integer> arrangeActivity(int[] s, int[] e) {
        int total = s.length;
        int endFlag = e[0];
        List<Integer> results = new ArrayList<>();
        results.add(0);
        for (int i = 0; i < total; i++) {
            if (s[i] > endFlag) {
                results.add(i);
                endFlag = e[i];
            }
        }
        return results;
    }

    /**
     * 找零钱问题]假如老板要找给我99分钱，他有上面的面值分别为25，10，5，1的硬币数，为了找给我最少的硬币数，那么他是不是该这样找呢，
     * 先看看该找多少个25分的，诶99／25＝3，好像是3个，要是4个的话，我们还得再给老板一个1分的，我不干，那么老板只能给我3个25分的拉，由于还少给我24，所以还得给我2个10分的和4个1分。
     *
     *
     */
    @Test
    public void count() {
        int[] money = {25, 10, 5, 1};
        int number = 65;
        List<Integer> list = gerResult(money, number);
        for (int i = 0;i < list.size();i ++) {
            System.out.println(list.get(i) + "张" + money[i]);
        }
    }

    public List<Integer> gerResult(int[] money, int number) {
        List<Integer> list = new ArrayList<>();
        int length =money.length;
        for (int i = 0;i < length;i ++) {
            int result = number / money[i];
            number = number % money[i];
            list.add(result);
        }
        return list;
    }


    /**
     * 背包问题
     */

}
