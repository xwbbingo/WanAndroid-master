package com.bingo.wanandroid.presenter.main;

import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.contract.main.SettingContract;
import com.bingo.wanandroid.core.DataManager;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/4/16
 */
public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    @Inject
    public SettingPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mDataManager.setAutoCacheState(b);
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean b) {
        mDataManager.setNoImageState(b);
    }
}
