package com.bingo.wanandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.bingo.wanandroid.app.WanAndroidApp;

/**
 * author bingo
 * date 2020/1/5
 */
public class NetWorkUtil {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
