package com.bingo.wanandroid.presenter.main;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.main.UsefulSitesContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.main.search.UsefulSiteData;

import java.util.List;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/4/15
 */
public class UsefulSitePresenter extends BasePresenter<UsefulSitesContract.View> implements UsefulSitesContract.Presenter {

    @Inject
    public UsefulSitePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getUsefulSiteData() {
        addSubscribe(mDataManager.getUsefulSiteData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<UsefulSiteData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_useful_sites_data)) {
                    @Override
                    public void onNext(List<UsefulSiteData> usefulSiteDataList) {
                        mView.showUsefulSiteData(usefulSiteDataList);
                    }
                }));
    }

    @Override
    public void attachView(UsefulSitesContract.View view) {
        super.attachView(view);
    }
}
