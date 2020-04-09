package com.bingo.wanandroid.contract.mainpager;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.mainpager.banner.BannerData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;

import java.util.List;

/**
 * author bingo
 * date 2020/1/3
 */
public interface MainPagerContract {

    interface View extends AbstractView{

        /**
         * 广告栏
         * @param bannerDataList 广告栏数据
         */
        void showBannerData(List<BannerData> bannerDataList);

        /**
         * 置顶文章
         * @param feedArticleDataList 置顶文章数据
         */
        void showTopArticleData(List<FeedArticleData> feedArticleDataList);

        /**
         * 文章列表
         * @param feedArticleListData 文章列表数据
         * @param isFresh 是否刷新
         */
        void showFeedArticleList(FeedArticleListData feedArticleListData, boolean isFresh);

        /**
         * Show auto login success
         */
        void showAutoLoginSuccess();

        /**
         * Show auto login fail
         */
        void showAutoLoginFail();

    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 加载主页数据
         */
        void loadMainPagerData();

        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getFeedArticleList(boolean isShowError);


        /**
         * Get top article data
         *
         * @param isShowError If show error
         */
        void getTopArticleData(boolean isShowError);

        /**
         * Get banner data
         *
         * @param isShowError If show error
         */
        void getBannerData(boolean isShowError);

        /**
         * Load more data
         */
        void loadMoreData();

        /**
         * Auto refresh 自动刷新显示错误,手动刷新不显示
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);
    }
}
