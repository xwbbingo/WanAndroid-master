package com.bingo.wanandroid.component;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.bingo.wanandroid.ui.main.activity.MainActivity;


/**
 * Activity的转场动画
 * author bingo
 * date 2020/2/9
 */
public class JudgeUtils {

    public static void startArticleDetailActivity(Context context, ActivityOptionsCompat compat, int id, String title, String link) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_ID,id);
        intent.putExtra(Constants.ARTICLE_TITLE,title);
        intent.putExtra(Constants.ARTICLE_LINK,link);
        if (compat != null)
            ActivityCompat.startActivity(context,intent,compat.toBundle());
        else
            context.startActivity(intent);
    }

    public static void startSearchListActivity(Context activity, String searchText) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(Constants.SEARCH_TEXT,searchText);
        activity.startActivity(intent);
    }
}
