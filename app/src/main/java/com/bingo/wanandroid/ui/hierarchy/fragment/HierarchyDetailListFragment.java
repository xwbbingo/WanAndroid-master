package com.bingo.wanandroid.ui.hierarchy.fragment;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.component.JudgeUtils;
import com.bingo.wanandroid.contract.hierarchy.HierarchyDetailListContract;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;
import com.bingo.wanandroid.presenter.hierarchy.HierarchyDetailListPresenter;
import com.bingo.wanandroid.ui.mainpager.adapter.FeedArticleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author bingo
 * date 2020/1/21
 */
public class HierarchyDetailListFragment extends BaseFragment<HierarchyDetailListPresenter>
        implements HierarchyDetailListContract.View {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.hierarchy_detail_list_recycler_view)
    RecyclerView mHierarchyDetailListRecyclerView;

    private int mCid;
    private FeedArticleAdapter mFeedArticleAdapter;

    public static HierarchyDetailListFragment newInstance(int param1, String param2) {
        Bundle args = new Bundle();
        args.putInt(Constants.PARAM1, param1);
        args.putString(Constants.PARAM2, param2);
        HierarchyDetailListFragment fragment = new HierarchyDetailListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy_detail_list;
    }

    @Override
    protected void initView() {
        super.initView();
        List<FeedArticleData> feedArticleDataList = new ArrayList<>();
        mFeedArticleAdapter = new FeedArticleAdapter(R.layout.item_article_common, feedArticleDataList);
        mHierarchyDetailListRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mHierarchyDetailListRecyclerView.setHasFixedSize(true);
        mHierarchyDetailListRecyclerView.setAdapter(mFeedArticleAdapter);

        mFeedArticleAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view,position));

        mFeedArticleAdapter.setOnItemChildClickListener(((adapter, view, position) -> {
            clickChildEvent(view,position);
        }));
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()){
            case R.id.item_article_common_chapterName:

                break;
            case R.id.item_article_common_collect:

                break;
            default:
                break;

        }
    }

    private void startArticleDetailPager(View view, int position) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        JudgeUtils.startArticleDetailActivity(_mActivity,
                options,
                mFeedArticleAdapter.getData().get(position).getId(),
                mFeedArticleAdapter.getData().get(position).getTitle(),
                mFeedArticleAdapter.getData().get(position).getLink()
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
        mPresenter.getHierarchyDetailList(mCid, true);
    }

    @Override
    public void showHierarchyDetailList(FeedArticleListData feedArticleListData, boolean isRefresh) {
        Log.i("test", feedArticleListData.getSize() + "  " + isRefresh);
        if (mFeedArticleAdapter == null)
            return;
        List<FeedArticleData> datas = feedArticleListData.getDatas();
        if (isRefresh) {
            mFeedArticleAdapter.replaceData(datas);
        } else {
            if (datas.size() > 0) {
                mFeedArticleAdapter.addData(datas);
            } else {
                showToast(getString(R.string.load_more_no_data));
            }
        }
    }
}
