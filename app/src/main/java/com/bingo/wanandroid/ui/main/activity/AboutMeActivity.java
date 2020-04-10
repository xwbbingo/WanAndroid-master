package com.bingo.wanandroid.ui.main.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.base.activity.AbstractActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author bingo
 * date 2020/4/7
 */
public class AboutMeActivity extends AbstractActivity {


    @BindView(R.id.about_me_toolbar)
    Toolbar mAboutMeToolbar;
    @BindView(R.id.about_me_app_bar)
    AppBarLayout mAboutMeAppBar;
    @BindView(R.id.aboutVersion)
    TextView mAboutVersion;
    @BindView(R.id.aboutContent)
    TextView mAboutContent;
    @BindView(R.id.about_me_fab)
    FloatingActionButton mAboutMeFab;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mAboutMeToolbar);
        //StatusBarUtil.immersive(this);
        //StatusBarUtil.setPaddingSmart(this,mAboutUsToolbar);
        mAboutMeToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        showAboutContent();
    }

    private void showAboutContent() {
        mAboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        mAboutContent.setMovementMethod(LinkMovementMethod.getInstance());
        try {
            String versionStr = getString(R.string.wan_android)
                    + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mAboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
