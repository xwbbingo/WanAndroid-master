package com.bingo.wanandroid.ui.mainpager.fragment;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.component.JudgeUtils;
import com.bingo.wanandroid.component.RxBus;
import com.bingo.wanandroid.contract.mainpager.MainPagerContract;
import com.bingo.wanandroid.core.bean.mainpager.banner.BannerData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;
import com.bingo.wanandroid.core.event.LoginEvent;
import com.bingo.wanandroid.presenter.mainpager.MainPagerPresenter;
import com.bingo.wanandroid.ui.mainpager.adapter.FeedArticleAdapter;
import com.bingo.wanandroid.widget.GlideImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author bingo
 * date 2020/1/5
 */
public class MainPagerFragment extends BaseFragment<MainPagerPresenter> implements MainPagerContract.View {


    @BindView(R.id.main_pager_recycler_view)
    RecyclerView mMainPagerRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private Banner mBanner;

    private FeedArticleAdapter mAdapter;
    private List<FeedArticleData> mTopArticleDataList;

    public static MainPagerFragment newInstance(boolean param1, String param2) {
        MainPagerFragment mainPagerFragment = new MainPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.PARAM1, param1);
        bundle.putString(Constants.PARAM2, param2);
        mainPagerFragment.setArguments(bundle);
        return mainPagerFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null)
            mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null)
            mBanner.stopAutoPlay();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    protected void initView() {
        super.initView();
        mTopArticleDataList = new ArrayList<>();
        List<FeedArticleData> mFeedArticleDataList = new ArrayList<>();
        mAdapter = new FeedArticleAdapter(R.layout.item_article_common, mFeedArticleDataList);
        mMainPagerRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mMainPagerRecyclerView.setHasFixedSize(true);
        //添加头部广告栏
        LinearLayout header = (LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.head_banner, null);
        mBanner = header.findViewById(R.id.main_page_head_banner);
        header.removeView(mBanner);
//        View  header = LayoutInflater.from(_mActivity).inflate(R.layout.head_banner_other, null);
//        mBanner = header.findViewById(R.id.main_page_head_banner);
//        mBanner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
        mAdapter.addHeaderView(mBanner);
        mMainPagerRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            startArticleDetailPager(view,position);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            clickChildEvent(view,position);
        });
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
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position)
            return;
        ActivityOptionsCompat compat =  ActivityOptionsCompat.makeSceneTransitionAnimation(_mActivity,view,getString(R.string.share_view));
        JudgeUtils.startArticleDetailActivity(_mActivity,
                compat,
                mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getTitle(),
                mAdapter.getData().get(position).getLink()
        );
    }

    @Override
    protected void initEventAndData() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false);
            refreshLayout.finishRefresh(1200);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMoreData();
            refreshLayout.finishLoadMore(1200);
        });
        mPresenter.loadMainPagerData();
    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        List<String> bannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        List<String> bannerUrlList = new ArrayList<>();

        for (BannerData bannerData:bannerDataList){
            bannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            bannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(bannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(bannerDataList.size()*500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        mBanner.setOnBannerListener(position -> {
            // TODO: 2020/1/15 跳转至文章详情页面
            JudgeUtils.startArticleDetailActivity(_mActivity,
                    null,
                    0,
                    bannerTitleList.get(position),
                    bannerUrlList.get(position)
                    );
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void showTopArticleData(List<FeedArticleData> feedArticleDataList) {
        if(mPresenter.getCurrentPage() == Constants.TYPE_MAIN_PAGER){
            mMainPagerRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mMainPagerRecyclerView.setVisibility(View.INVISIBLE);
        }
        if (mAdapter == null)
            return;
        //置顶数据
        mTopArticleDataList.clear();
        mTopArticleDataList.addAll(feedArticleDataList);
    }

    @Override
    public void showFeedArticleList(FeedArticleListData feedArticleListData, boolean isFresh) {
        if(mPresenter.getCurrentPage() == Constants.TYPE_MAIN_PAGER){
            mMainPagerRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mMainPagerRecyclerView.setVisibility(View.INVISIBLE);
        }
        if (mAdapter ==null)
            return;
        List<FeedArticleData> newDatas = feedArticleListData.getDatas();
        if (isFresh){
            List<FeedArticleData> allNewDatas = new ArrayList<>();
            allNewDatas.addAll(mTopArticleDataList);
            allNewDatas.addAll(newDatas);
            mAdapter.replaceData(allNewDatas);
        } else {
            mAdapter.addData(newDatas);
        }
    }

    @Override
    public void showAutoLoginSuccess() {
        if (isAdded()){
            showSnackBar(getString(R.string.auto_login_success));
            RxBus.getDefault().post(new LoginEvent(true));
        }
    }

    @Override
    public void showAutoLoginFail() {
        RxBus.getDefault().post(new LoginEvent(false));
    }

    public void jumpToTheTop() {
        if (mMainPagerRecyclerView != null)
            mMainPagerRecyclerView.smoothScrollToPosition(0);
    }
}
