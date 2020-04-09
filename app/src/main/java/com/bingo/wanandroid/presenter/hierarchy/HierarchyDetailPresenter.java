package com.bingo.wanandroid.presenter.hierarchy;

import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.contract.hierarchy.HierarchyDetailContact;
import com.bingo.wanandroid.core.DataManager;


import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/17
 */
public class HierarchyDetailPresenter extends BasePresenter<HierarchyDetailContact.View>
        implements HierarchyDetailContact.Presenter {

    @Inject
    public HierarchyDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(HierarchyDetailContact.View view) {
        super.attachView(view);
    }
}
