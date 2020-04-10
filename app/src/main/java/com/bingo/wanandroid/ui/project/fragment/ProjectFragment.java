package com.bingo.wanandroid.ui.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.contract.project.ProjectContract;
import com.bingo.wanandroid.core.bean.project.ProjectClassifyData;
import com.bingo.wanandroid.presenter.project.ProjectPresenter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author bingo
 * date 2020/1/21
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mProjectTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mProjectViewpager;
    @BindView(R.id.refresh_layout)
    LinearLayout mRefreshLayout;

    private List<BaseFragment> mFragments;
    private int mCurrentPage;

    public static ProjectFragment newInstance(String param1, String param2) {
        Bundle args = new Bundle();
        args.putString(Constants.PARAM1, param1);
        args.putString(Constants.PARAM2, param2);
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initEventAndData() {
        mFragments = new ArrayList<>();
        mPresenter.getProjectClassifyData(true);
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList) {
        if(mPresenter.getCurrentPage() == Constants.TYPE_PROJECT){
            mRefreshLayout.setVisibility(View.VISIBLE);
        } else {
            mRefreshLayout.setVisibility(View.GONE);
        }
        initViewPagerAndTabLayout(projectClassifyDataList);
    }

    private void initViewPagerAndTabLayout(List<ProjectClassifyData> datas) {
        for (ProjectClassifyData data : datas
             ) {
            ProjectListFragment projectListFragment = ProjectListFragment.newInstance(data.getId(), null);
            mFragments.add(projectListFragment);
        }
        mProjectViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return datas.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return datas.get(position).getName();
            }
        });
        mProjectViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mProjectTabLayout.setViewPager(mProjectViewpager);
        mProjectViewpager.setCurrentItem(0);
    }


}
