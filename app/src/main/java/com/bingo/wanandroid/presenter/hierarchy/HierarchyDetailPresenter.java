package com.bingo.wanandroid.presenter.hierarchy;

import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.contract.hierarchy.HierarchyDetailContract;
import com.bingo.wanandroid.core.DataManager;


import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/17
 */
public class HierarchyDetailPresenter extends BasePresenter<HierarchyDetailContract.View>
        implements HierarchyDetailContract.Presenter {

    @Inject
    public HierarchyDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(HierarchyDetailContract.View view) {
        super.attachView(view);
    }
}
