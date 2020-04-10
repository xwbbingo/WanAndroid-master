package com.bingo.wanandroid.presenter.navigation;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.navigation.NavigationContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.navigation.NavigationData;

import java.util.List;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/21
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {

    @Inject
    public NavigationPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getNavigationData(boolean isShowError) {
        addSubscribe(mDataManager.getNavigationData()
        .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult())
        .subscribeWith(new BaseObserver<List<NavigationData>>(mView,
                WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_navigation_list)
                ,isShowError) {
            @Override
            public void onNext(List<NavigationData> navigationData) {
                mView.showNavigationData(navigationData);
            }
        }));
    }
}
