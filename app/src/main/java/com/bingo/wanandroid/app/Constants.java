package com.bingo.wanandroid.app;

import android.graphics.Color;

import com.bingo.wanandroid.R;

import java.io.File;

/**
 * author bingo
 * date 2020/1/3
 * 常量
 */
public class Constants {

    /**
     * Tag fragment classify
     */
    public static final int TYPE_MAIN_PAGER = 0;

    public static final int TYPE_KNOWLEDGE = 1;

    public static final int TYPE_NAVIGATION = 2;

    public static final int TYPE_PROJECT = 3;

    public static final int TYPE_WX_ARTICLE = 4;

    public static final int TYPE_COLLECT = 5;

    public static final int TYPE_SETTING = 6;

    /**
     * Path
     */
    public static final String PATH_DATA = WanAndroidApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * SharedPreferences path
     */
    public static final String WAN_ANDROID_SP = "wan_android_sp" ;
    public static final String NIGHT_MODE_STATE = "night_mode_state";
    public static final String LOGIN_STATUS = "login_status";
    public static final String LOGIN_ACCOUNT = "login_account";
    public static final String LOGIN_PASSWORD = "login_password";
    public static final String CURRENT_PAGE = "current_page";

    /**
     * Main Pager
     */

    public static final String LOGIN_DATA = "login_data";

    public static final String BANNER_DATA = "banner_data";

    public static final String TOP_DATA = "top_data";

    public static final String ARTICLE_DATA = "article_data";

    /**
     * Params    Activity -> Fragment
     */
    public static final String PARAM1 = "param1";

    public static final String PARAM2 = "param2";

    /**
     * Article Detail
     */
    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_ID = "article_id";

    /**
     * Refresh theme color
     */
    public static final int BLUE_THEME = R.color.colorPrimary;

    /**
     * Avoid double click time area
     */
    public static final long CLICK_TIME_AREA = 1000;

    public static final long DOUBLE_INTERVAL_TIME = 2000;

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
