package com.bingo.wanandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * author bingo
 * date 2020/4/20
 */
public class ShareUtil {

    private static final String EMAIL_ADDRESS = "401917941@qq.com";

    public static void shareText(Context context,String text,String title){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("type/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static void shareEmail(Context context,String title){
        Intent intent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("mailto:" + EMAIL_ADDRESS));
        context.startActivity(Intent.createChooser(intent,title));
    }
}
