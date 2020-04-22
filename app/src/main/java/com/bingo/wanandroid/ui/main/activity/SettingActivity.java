package com.bingo.wanandroid.ui.main.activity;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.component.ACache;
import com.bingo.wanandroid.contract.main.SettingContract;
import com.bingo.wanandroid.presenter.main.SettingPresenter;
import com.bingo.wanandroid.utils.ShareUtil;
import com.bingo.wanandroid.utils.StatusBarUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 缓存模式、无图模式用于 WebView.
 * author bingo
 * date 2020/4/16
 */
public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.View, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.setting_toolbar)
    Toolbar mSettingToolbar;
    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    TextView mLlSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView mTvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;

    private File cacheFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mSettingToolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mSettingToolbar);
        mSettingToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        cacheFile = new File(Constants.PATH_CACHE);
        mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
        mCbSettingCache.setChecked(mPresenter.getAutoCacheState());
        mCbSettingImage.setChecked(mPresenter.getNoImageState());
        mCbSettingNight.setChecked(mPresenter.getNightModeState());
        mCbSettingCache.setOnCheckedChangeListener(this);
        mCbSettingImage.setOnCheckedChangeListener(this);
        mCbSettingNight.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_cache:
                mPresenter.setAutoCacheState(isChecked);
                break;
            case R.id.cb_setting_image:
                mPresenter.setNoImageState(isChecked);
                break;
            case R.id.cb_setting_night:
                mPresenter.setNightModeState(isChecked);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.ll_setting_feedback, R.id.ll_setting_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_feedback:
                ShareUtil.shareEmail(this, getString(R.string.send_email));
                break;
            case R.id.ll_setting_clear:
                ACache.deleteDir(cacheFile);
                mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
                break;
        }
    }
}
