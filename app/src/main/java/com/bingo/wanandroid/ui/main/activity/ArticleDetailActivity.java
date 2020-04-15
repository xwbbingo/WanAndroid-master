package com.bingo.wanandroid.ui.main.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.contract.main.ArticleDetailContract;
import com.bingo.wanandroid.presenter.main.ArticleDetailPresenter;
import com.bingo.wanandroid.utils.StatusBarUtil;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author bingo
 * date 2020/2/9
 */
public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter>
        implements ArticleDetailContract.View {

    @BindView(R.id.article_detail_web_view)
    FrameLayout mArticleDetailWebView;
    @BindView(R.id.article_detail_toolbar)
    Toolbar mArticleDetailToolbar;


    private AgentWeb mAgentWeb;
    private int mId;
    private String mTitle;
    private String mLink;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolbar() {
        getBundleData();
        setSupportActionBar(mArticleDetailToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this,R.color.main_status_bar_blue),1f);
        mArticleDetailToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        mArticleDetailToolbar.setTitle(Html.fromHtml(mTitle));
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        mTitle = bundle.getString(Constants.ARTICLE_TITLE);
        mLink = bundle.getString(Constants.ARTICLE_LINK);
        mId = bundle.getInt(Constants.ARTICLE_ID);
    }

    @Override
    protected void initEventAndData() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mArticleDetailWebView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                //.setMainFrameErrorView()
                .createAgentWeb()
                .ready()
                .go(mLink);
        WebView webView = mAgentWeb.getWebCreator().getWebView();
        WebSettings settings = webView.getSettings();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_article_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event))
            return true;
        return super.onKeyDown(keyCode, event);
    }

    //跟随 Activity Or Fragment 生命周期 ， 释放 CPU 更省电
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
