package com.bingo.wanandroid.contract.main;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.main.search.HotSearchData;
import com.bingo.wanandroid.core.bean.main.search.UsefulSiteData;

import java.util.List;

/**
 * author bingo
 * date 2020/4/10
 */
public interface UsefulSitesContract {

    interface View extends AbstractView{

        /**
         * 显示常用网站
         * @param usefulSiteDataList 常用网站数据
         */
        void showUsefulSiteData(List<UsefulSiteData> usefulSiteDataList);

    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取常用网站数据
         */
        void getUsefulSiteData();
    }
}
