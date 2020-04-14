package com.bingo.wanandroid.ui.hierarchy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.contract.hierarchy.HierarchyDetailContract;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;
import com.bingo.wanandroid.presenter.hierarchy.HierarchyDetailPresenter;
import com.bingo.wanandroid.ui.hierarchy.fragment.HierarchyDetailListFragment;
import com.bingo.wanandroid.utils.StatusBarUtil;
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
public class HierarchyDetailActivity extends BaseActivity<HierarchyDetailPresenter> implements HierarchyDetailContract.View {

    @BindView(R.id.hierarchy_detail_tab_layout)
    SlidingTabLayout mHierarchyDetailTabLayout;
    @BindView(R.id.hierarchy_detail_viewpager)
    ViewPager mHierarchyDetailViewpager;
    @BindView(R.id.hierarchy_floating_action_btn)
    FloatingActionButton mHierarchyFloatingActionBtn;
    @BindView(R.id.hierarchy_detail_toolbar)
    Toolbar mHierarchyDetailToolbar;

    private List<BaseFragment> mFragments;
    private List<HierarchyData.HierarchyChildren> mChildren;
    private int mCurrentPage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hierarchy_detail;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mHierarchyDetailToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this,R.color.main_status_bar_blue),1f);
        mHierarchyDetailToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
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
        mHierarchyDetailToolbar.setTitle(hierarchyData.getName().trim());
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
        mHierarchyDetailViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mHierarchyDetailTabLayout.setViewPager(mHierarchyDetailViewpager);
        mHierarchyDetailViewpager.setCurrentItem(0);
    }

    @OnClick({R.id.hierarchy_floating_action_btn})
    public void onViewClicked(View view) {
        //activity内点击,相应的在某个fragment滑至顶部
        ((HierarchyDetailListFragment) mFragments.get(mCurrentPage)).jumpToTheTop();
    }

}
