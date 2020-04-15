package com.bingo.wanandroid.presenter.project;

import android.util.Log;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.project.ProjectListContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.project.ProjectListData;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/2/3
 */
public class ProjectListPresenter extends BasePresenter<ProjectListContract.View> implements ProjectListContract.Presenter {

    private int mCurrentPage = 1; //拼接页,从1开始
    private boolean isRefresh = true;

    @Inject
    public ProjectListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(ProjectListContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getProjectList(int cid, boolean isShowError) {
        addSubscribe(mDataManager.getProjectList(mCurrentPage, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<ProjectListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_project_list),
                        isShowError) {
                    @Override
                    public void onNext(ProjectListData projectListData) {
                        mView.showProjectList(projectListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMoreData(int cid) {
        mCurrentPage++;
        isRefresh = false;
        getProjectList(cid, false);
    }

    @Override
    public void pullToRefresh(int cid) {
        mCurrentPage = 1;
        isRefresh = true;
        getProjectList(cid, false);
    }
}
