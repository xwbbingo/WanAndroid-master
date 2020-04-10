package com.bingo.wanandroid.contract.hierarchy;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;
import com.bingo.wanandroid.core.bean.project.ProjectListData;

/**
 * author bingo
 * date 2020/1/17
 */
public interface HierarchyDetailListContract {

    interface View extends AbstractView{
        /**
         * 展示体系列表
         * @param feedArticleListData 体系列表数据
         * @param isRefresh 是否刷新
         */
        void showHierarchyDetailList(FeedArticleListData feedArticleListData, boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<View>{

        /**
        * 获取体系列表数据
        * @param cid 体系二级目录的id
        * @param isShowError 是否显示错误
        */
        void getHierarchyDetailList(int cid, boolean isShowError);

        /**
         * 加载更多数据
         */
        void loadMoreData(int cid);

        /**
         * 下拉刷新
         */
        void pullToRefresh(int cid);
    }
}
