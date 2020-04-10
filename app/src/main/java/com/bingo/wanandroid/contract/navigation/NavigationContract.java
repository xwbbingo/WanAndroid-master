package com.bingo.wanandroid.contract.navigation;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;
import com.bingo.wanandroid.core.bean.navigation.NavigationData;

import java.util.List;

/**
 * author bingo
 * date 2020/1/17
 */
public interface NavigationContract {

    interface View extends AbstractView{

        /**
         * 展示导航列表
         * @param navigationDataList 导航列表数据
         */
        void showNavigationData(List<NavigationData> navigationDataList);
    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取导航列表
         * @param isShowError 是否显示错误
         */
        void getNavigationData(boolean isShowError);
    }
}
