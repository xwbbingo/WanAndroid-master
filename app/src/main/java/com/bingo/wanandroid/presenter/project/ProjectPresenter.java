package com.bingo.wanandroid.presenter.project;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.project.ProjectContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.project.ProjectClassifyData;

import java.util.List;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/21
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    @Inject
    public ProjectPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProjectClassifyData(boolean isShowError) {
        addSubscribe(mDataManager.getProjectClassifyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<ProjectClassifyData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_project_classify_data)
                        , isShowError) {
                    @Override
                    public void onNext(List<ProjectClassifyData> projectClassifyDataList) {
                        mView.showProjectClassifyData(projectClassifyDataList);
                    }
                }));
    }
}
