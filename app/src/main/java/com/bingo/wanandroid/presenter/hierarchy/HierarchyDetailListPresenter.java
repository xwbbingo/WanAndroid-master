package com.bingo.wanandroid.presenter.hierarchy;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.hierarchy.HierarchyDetailListContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/17
 */
public class HierarchyDetailListPresenter extends BasePresenter<HierarchyDetailListContract.View>
        implements HierarchyDetailListContract.Presenter {

    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public HierarchyDetailListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(HierarchyDetailListContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getHierarchyDetailList(int cid, boolean isShowError) {
        addSubscribe(mDataManager.getHierarchyDetailList(mCurrentPage, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showHierarchyDetailList(feedArticleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMoreData(int cid) {
        mCurrentPage ++;
        isRefresh = false;
        getHierarchyDetailList(cid, false);
    }

    @Override
    public void pullToRefresh(int cid) {
        mCurrentPage = 0;
        isRefresh = true;
        getHierarchyDetailList(cid, false);
    }
}
