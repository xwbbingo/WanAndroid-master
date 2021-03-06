package com.bingo.wanandroid.ui.project.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.component.JudgeUtils;
import com.bingo.wanandroid.contract.project.ProjectListContract;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.project.ProjectListData;
import com.bingo.wanandroid.presenter.project.ProjectListPresenter;
import com.bingo.wanandroid.ui.project.adapter.ProjectListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author bingo
 * date 2020/1/21
 */
public class ProjectListFragment extends BaseFragment<ProjectListPresenter> implements ProjectListContract.View {


    @BindView(R.id.project_list_recycler_view)
    RecyclerView mProjectListRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private ProjectListAdapter mProjectListAdapter;
    private int mCid;

    public static ProjectListFragment newInstance(int param1, String param2) {
        Bundle args = new Bundle();
        args.putInt(Constants.PARAM1, param1);
        args.putString(Constants.PARAM2, param2);
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initView() {
        super.initView();
        List<FeedArticleData> feedArticleDataList = new ArrayList<>();
        mProjectListAdapter = new ProjectListAdapter(R.layout.item_project_list, feedArticleDataList);
        mProjectListRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mProjectListRecyclerView.setHasFixedSize(true);
        mProjectListRecyclerView.setAdapter(mProjectListAdapter);
        mProjectListAdapter.setOnItemClickListener(((adapter, view, position) -> {
            startProjectDetailPager(view,position);
        }));
        mProjectListAdapter.setOnItemChildClickListener(((adapter, view, position) -> clickChildEvent(view,position)));
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()){
            case R.id.item_project_list_install:
                startInstallPager(position);
                break;
        }
    }

    private void startInstallPager(int position) {
        if (mProjectListAdapter.getData().size() <=0 || mProjectListAdapter.getData().size()<=position)
            return;
        String apkLink = mProjectListAdapter.getData().get(position).getApkLink();
        if (!TextUtils.isEmpty(apkLink))
            return;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(apkLink)));
    }

    private void startProjectDetailPager(View view, int position) {
        if(mProjectListAdapter.getData().size() <= 0 || mProjectListAdapter.getData().size() <= position)
            return;
        ActivityOptionsCompat compat =  ActivityOptionsCompat.makeSceneTransitionAnimation(_mActivity,view,getString(R.string.share_view));
        JudgeUtils.startArticleDetailActivity(_mActivity,
                compat,
                mProjectListAdapter.getData().get(position).getId(),
                mProjectListAdapter.getData().get(position).getTitle(),
                mProjectListAdapter.getData().get(position).getLink()
        );
    }

    @Override
    protected void initEventAndData() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.pullToRefresh(mCid);
            refreshLayout.finishRefresh(1200);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMoreData(mCid);
            refreshLayout.finishLoadMore(1200);
        });
        Bundle bundle = getArguments();
        assert bundle != null;
        mCid = bundle.getInt(Constants.PARAM1);
        mPresenter.getProjectList(mCid,true);
    }

    @Override
    public void showProjectList(ProjectListData projectListData, boolean isRefresh) {
        if (mProjectListAdapter == null)
            return;
        List<FeedArticleData> datas = projectListData.getDatas();
        if (isRefresh){
            mProjectListAdapter.replaceData(datas);
        } else {
            if (datas.size() > 0){
                mProjectListAdapter.addData(datas);
            } else {
                showToast(getString(R.string.load_more_no_data));
            }
        }
    }

    public void jumpToTheTop(){
        if (mProjectListRecyclerView != null)
            mProjectListRecyclerView.smoothScrollToPosition(0);
    }

}
