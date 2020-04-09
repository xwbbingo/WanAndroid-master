package com.bingo.wanandroid.contract.hierarchy;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;

import java.util.List;

/**
 * author bingo
 * date 2020/1/17
 */
public interface HierarchyContact {

    interface View extends AbstractView{

        /**
         * 展示体系列表
         * @param hierarchyDataList 体系列表数据
         */
        void showHierarchyData(List<HierarchyData> hierarchyDataList);
    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取知识列表
         * @param isShowError 是否显示错误
         */
        void getHierarchyData(boolean isShowError);
    }
}
