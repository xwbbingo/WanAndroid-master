package com.bingo.wanandroid.ui.hierarchy.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.contract.hierarchy.HierarchyContract;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;
import com.bingo.wanandroid.presenter.hierarchy.HierarchyPresenter;
import com.bingo.wanandroid.ui.hierarchy.activity.HierarchyDetailActivity;
import com.bingo.wanandroid.ui.hierarchy.adapter.HierarchyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author bingo
 * date 2020/1/17
 */
public class HierarchyFragment extends BaseFragment<HierarchyPresenter> implements HierarchyContract.View {


    @BindView(R.id.hierarchy_recycler_view)
    RecyclerView mHierarchyRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private HierarchyAdapter mAdapter;
    private boolean isRefresh = true;

    public static HierarchyFragment newInstance(String param1,String param2) {

        Bundle args = new Bundle();
        args.putString(Constants.PARAM1,param1);
        args.putString(Constants.PARAM2,param2);
        HierarchyFragment fragment = new HierarchyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy;
    }

    @Override
    protected void initView() {
        super.initView();
        List<HierarchyData> mHierarchyDataList = new ArrayList<>();
        mAdapter = new HierarchyAdapter(R.layout.item_hierarchy, mHierarchyDataList);
        mHierarchyRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mHierarchyRecyclerView.setHasFixedSize(true);
        mHierarchyRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startHierarchyPager(view,position));
    }

    private void startHierarchyPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        Intent intent = new Intent(_mActivity, HierarchyDetailActivity.class);
        intent.putExtra(Constants.PARAM1,mAdapter.getData().get(position));
        startActivity(intent,options.toBundle());
    }

    @Override
    protected void initEventAndData() {
        //mRefreshLayout.setPrimaryColorsId(Constants.BLUE_THEME, R.color.white);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            mPresenter.getHierarchyData(false);
            refreshLayout.finishRefresh(1200);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            mPresenter.getHierarchyData(false);
            refreshLayout.finishLoadMore(1200);
        });
        mPresenter.getHierarchyData(true);
    }

    @Override
    public void showHierarchyData(List<HierarchyData> hierarchyDataList) {
        if (mPresenter.getCurrentPage() == Constants.TYPE_KNOWLEDGE){
            mHierarchyRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mHierarchyRecyclerView.setVisibility(View.INVISIBLE);
        }
        if (mAdapter == null)
            return;
        if (isRefresh && mAdapter.getData().size() <= hierarchyDataList.size()){
            mAdapter.replaceData(hierarchyDataList);
        } else {
            showToast(getString(R.string.load_more_no_data));
        }
    }

    public void jumpToTheTop() {
        if (mHierarchyRecyclerView != null)
            mHierarchyRecyclerView.smoothScrollToPosition(0);
    }
}
