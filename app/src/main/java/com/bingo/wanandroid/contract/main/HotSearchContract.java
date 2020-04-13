package com.bingo.wanandroid.contract.main;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.main.search.HotSearchData;

import java.util.List;

/**
 * author bingo
 * date 2020/4/10
 */
public interface HotSearchContract {

    interface View extends AbstractView{

        /**
         * 显示热搜
         * @param hotSearchDataList 热搜数据
         */
        void showHotSearchData(List<HotSearchData> hotSearchDataList);

    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取热搜数据
         */
        void getHotSearchData();
    }
}
