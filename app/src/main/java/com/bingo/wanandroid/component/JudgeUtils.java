package com.bingo.wanandroid.component;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.bingo.wanandroid.ui.main.activity.MainActivity;


/**
 * author bingo
 * date 2020/2/9
 */
public class JudgeUtils {

    public static void startArticleDetailActivity(Context context, ActivityOptions options, int id, String title, String link) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_ID,id);
        intent.putExtra(Constants.ARTICLE_TITLE,title);
        intent.putExtra(Constants.ARTICLE_LINK,link);
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            context.startActivity(intent,options.toBundle());
        } else {
            context.startActivity(intent);
        }
    }

    public static void startSearchListActivity(Context activity, String searchText) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(Constants.SEARCH_TEXT,searchText);
        activity.startActivity(intent);
    }
}
