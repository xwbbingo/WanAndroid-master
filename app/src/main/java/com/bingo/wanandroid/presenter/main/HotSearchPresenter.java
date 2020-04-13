package com.bingo.wanandroid.presenter.main;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.main.HotSearchContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.main.search.HotSearchData;

import java.util.List;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/4/10
 */
public class HotSearchPresenter extends BasePresenter<HotSearchContract.View> implements HotSearchContract.Presenter {


    @Inject
    public HotSearchPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getHotSearchData() {
        addSubscribe(mDataManager.getHotSearchData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<HotSearchData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_hot_data)) {
                    @Override
                    public void onNext(List<HotSearchData> hotSearchDataList) {
                        mView.showHotSearchData(hotSearchDataList);
                    }
                }));
    }

    @Override
    public void attachView(HotSearchContract.View view) {
        super.attachView(view);
    }
}
