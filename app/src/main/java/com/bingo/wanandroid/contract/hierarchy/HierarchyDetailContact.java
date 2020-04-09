package com.bingo.wanandroid.contract.hierarchy;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;

import java.util.List;

/**
 * author bingo
 * date 2020/1/17
 */
public interface HierarchyDetailContact {

    interface View extends AbstractView{
    }

    interface Presenter extends AbstractPresenter<View>{
    }
}
