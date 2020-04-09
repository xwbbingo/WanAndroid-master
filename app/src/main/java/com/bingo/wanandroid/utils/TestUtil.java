package com.bingo.wanandroid.utils;

import android.app.Activity;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * author bingo
 * date 2020/1/9
 */
public class TestUtil {

    public void test(){

        HashMap<String,String> map = new HashMap();
        map.put("1","123");
        map.put("1","234");
        map.get("1");
    }

    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();
        testUtil.test();
    }


}
