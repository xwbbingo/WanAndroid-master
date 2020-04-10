package com.bingo.wanandroid.presenter.hierarchy;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.hierarchy.HierarchyContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;

import java.util.List;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/17
 */
public class HierarchyPresenter extends BasePresenter<HierarchyContract.View> implements HierarchyContract.Presenter {

    @Inject
    public HierarchyPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(HierarchyContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getHierarchyData(boolean isShowError) {
        addSubscribe(mDataManager.getHierarchyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<HierarchyData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(List<HierarchyData> hierarchyDataList) {
                        mView.showHierarchyData(hierarchyDataList);
                    }
                }));
    }

}
