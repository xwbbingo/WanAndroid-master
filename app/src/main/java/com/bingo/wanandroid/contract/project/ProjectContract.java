package com.bingo.wanandroid.contract.project;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.navigation.NavigationData;
import com.bingo.wanandroid.core.bean.project.ProjectClassifyData;
import com.bingo.wanandroid.core.bean.project.ProjectListData;

import java.util.List;

/**
 * author bingo
 * date 2020/1/17
 */
public interface ProjectContract {

    interface View extends AbstractView{

        /**
         * 展示项目分类列表
         * @param projectClassifyDataList 项目分类列表数据
         */
        void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList);
    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取项目分类数据
         * @param isShowError 是否显示错误
         */
        void getProjectClassifyData(boolean isShowError);

    }
}
