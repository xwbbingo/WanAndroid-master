package com.bingo.wanandroid.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bingo.wanandroid.component.ActivityControl;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * author bingo
 * date 2020/1/2
 */
public abstract class AbstractActivity extends SupportActivity {

    private Unbinder unbinder;
    protected AbstractActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mActivity = this;
        ActivityControl.getInstance().addActivity(this);
        onViewCreated();
        initToolbar();
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.getInstance().removeActivity(this);
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    /**
     * 当前Activity的UI布局
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据前调用
     */
    protected abstract void onViewCreated();

    /**
     * 初始化ToolBar
     */
    protected abstract void initToolbar();

    /**
     * 初始化事件和数的
     */
    protected abstract void initEventAndData();

}
