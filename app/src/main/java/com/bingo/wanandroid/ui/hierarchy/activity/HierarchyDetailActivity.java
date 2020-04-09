package com.bingo.wanandroid.ui.hierarchy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.contract.hierarchy.HierarchyDetailContact;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;
import com.bingo.wanandroid.presenter.hierarchy.HierarchyDetailPresenter;
import com.bingo.wanandroid.ui.hierarchy.fragment.HierarchyDetailListFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author bingo
 * date 2020/2/6
 */
public class HierarchyDetailActivity extends BaseActivity<HierarchyDetailPresenter> implements HierarchyDetailContact.View {

    @BindView(R.id.common_toolbar_title_tv)
    TextView mCommonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar mCommonToolbar;
    @BindView(R.id.hierarchy_detail_tab_layout)
    SlidingTabLayout mHierarchyDetailTabLayout;
    @BindView(R.id.hierarchy_detail_viewpager)
    ViewPager mHierarchyDetailViewpager;
    @BindView(R.id.hierarchy_floating_action_btn)
    FloatingActionButton mHierarchyFloatingActionBtn;

    private List<BaseFragment> mFragments;
    private List<HierarchyData.HierarchyChildren> mChildren;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hierarchy_detail;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mCommonToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        //StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this,R.color.blue_dark_btn),1f);
        mCommonToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        mFragments = new ArrayList<>();
        startNormalHierarchyListPager();
    }

    /**
     * 装载多个列表的知识体系页面（Hierarchy进入）
     */
    private void startNormalHierarchyListPager() {
        HierarchyData hierarchyData = (HierarchyData) getIntent().getSerializableExtra(Constants.PARAM1);
        if (hierarchyData == null || hierarchyData.getName() == null)
            return;
        mCommonToolbarTitleTv.setText(hierarchyData.getName().trim());
        mChildren = hierarchyData.getChildren();
        if (mChildren == null)
            return;
        for (HierarchyData.HierarchyChildren data : mChildren) {
            mFragments.add(HierarchyDetailListFragment.newInstance(data.getId(), null));
        }
    }

    @Override
    protected void initEventAndData() {
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        mHierarchyDetailViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mChildren.get(position).getName();
            }
        });
        mHierarchyDetailTabLayout.setViewPager(mHierarchyDetailViewpager);
        mHierarchyDetailViewpager.setCurrentItem(0);
    }

    @OnClick({R.id.hierarchy_floating_action_btn})
    public void onViewClicked() {
        //activity内点击,相应的在某个fragment滑至顶部

    }
}
