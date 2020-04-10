package com.bingo.wanandroid.contract.project;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.project.ProjectClassifyData;
import com.bingo.wanandroid.core.bean.project.ProjectListData;

import java.util.List;

/**
 * author bingo
 * date 2020/1/17
 */
public interface ProjectListContract {

    interface View extends AbstractView{

        /**
         * 展示项目列表
         * @param projectListData 项目列表数据
         * @param isRefresh 是否刷新
         */
        void showProjectList(ProjectListData projectListData, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取项目列表数据
         * @param cid 项目分类的id
         * @param isShowError 是否显示错误
         */
        void getProjectList(int cid, boolean isShowError);

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
