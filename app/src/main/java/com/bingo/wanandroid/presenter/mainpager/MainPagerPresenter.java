package com.bingo.wanandroid.presenter.mainpager;

import android.support.annotation.NonNull;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxBus;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.mainpager.MainPagerContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.BaseResponse;
import com.bingo.wanandroid.core.bean.mainpager.banner.BannerData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;
import com.bingo.wanandroid.core.bean.main.login.LoginData;
import com.bingo.wanandroid.core.event.LoginEvent;
import com.bingo.wanandroid.utils.CommonUtil;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function4;

/**
 * author bingo
 * date 2020/1/5
 */
public class MainPagerPresenter extends BasePresenter<MainPagerContract.View> implements MainPagerContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;//当前页面。下拉重置为0,上拉加载加1。
    private boolean isRefresh = true;//刷新或加载更多

    @Inject
    public MainPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void loadMainPagerData() {
        Observable<BaseResponse<LoginData>> loginDataObservable = mDataManager.getLoginData(getLoginAccount(), getLoginPassword());
        Observable<BaseResponse<List<BannerData>>> mBannerListObservable = mDataManager.getBannerData();
        Observable<BaseResponse<List<FeedArticleData>>> mTopArticleListObservable = mDataManager.getTopArticleData();
        Observable<BaseResponse<FeedArticleListData>> mFeedArticleListObservable = mDataManager.getFeedArticleList(mCurrentPage);
        addSubscribe(Observable.zip(loginDataObservable, mBannerListObservable,
                mTopArticleListObservable, mFeedArticleListObservable,
                new Function4<BaseResponse<LoginData>,
                        BaseResponse<List<BannerData>>,
                        BaseResponse<List<FeedArticleData>>,
                        BaseResponse<FeedArticleListData>, HashMap<String, Object>>() {
                    @Override
                    public HashMap<String, Object> apply(BaseResponse<LoginData> loginResponse,
                                                         BaseResponse<List<BannerData>> bannerResponse,
                                                         BaseResponse<List<FeedArticleData>> topArticleResponse,
                                                         BaseResponse<FeedArticleListData> feedArticleListResponse) throws Exception {
                        return MainPagerPresenter.this.createResponseMap(loginResponse, bannerResponse, topArticleResponse, feedArticleListResponse);
                    }
                })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {
                        BaseResponse<LoginData> loginResponse = CommonUtil.cast(map.get(Constants.LOGIN_DATA));
                        if (loginResponse.getErrorCode() == BaseResponse.SUCCESS) {
                            loginSuccess(loginResponse);
                        } else {
                            setLoginStatus(false);
                            mView.showAutoLoginFail();
                        }
                        BaseResponse<List<BannerData>> bannerResponse = CommonUtil.cast(map.get(Constants.BANNER_DATA));
                        if (bannerResponse != null) {
                            mView.showBannerData(bannerResponse.getData());
                        }
                        BaseResponse<List<FeedArticleData>> topResponse = CommonUtil.cast(map.get(Constants.TOP_DATA));
                        if (topResponse != null) {
                            mView.showTopArticleData(topResponse.getData());
                        }
                        BaseResponse<FeedArticleListData> feedArticleListResponse = CommonUtil.cast(map.get(Constants.ARTICLE_DATA));
                        if (feedArticleListResponse != null) {
                            mView.showFeedArticleList(feedArticleListResponse.getData(), isRefresh);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showAutoLoginFail();
                    }
                }));
    }

    private void loginSuccess(BaseResponse<LoginData> loginResponse) {
        LoginData loginData = loginResponse.getData();
        mDataManager.setLoginAccount(loginData.getUsername());
        mDataManager.setLoginPassword(loginData.getPassword());
        mDataManager.setLoginStatus(true);
        mView.showAutoLoginSuccess();
    }

    @NonNull
    private HashMap<String, Object> createResponseMap(BaseResponse<LoginData> loginResponse,
                                                      BaseResponse<List<BannerData>> bannerResponse,
                                                      BaseResponse<List<FeedArticleData>> topArticleResponse,
                                                      BaseResponse<FeedArticleListData> feedArticleListResponse) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.LOGIN_DATA, loginResponse);
        map.put(Constants.BANNER_DATA, bannerResponse);
        map.put(Constants.TOP_DATA, topArticleResponse);
        map.put(Constants.ARTICLE_DATA, feedArticleListResponse);
        return map;
    }

    @Override
    public void getFeedArticleList(boolean isShowError) {
        addSubscribe(mDataManager.getFeedArticleList(mCurrentPage)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(feedArticleListResponse -> mView != null)
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        mView.showFeedArticleList(feedArticleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void getTopArticleData(boolean isShowError) {
        addSubscribe(mDataManager.getTopArticleData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<FeedArticleData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_top_data), isShowError) {
                    @Override
                    public void onNext(List<FeedArticleData> topFeedArticleDataList) {
                        mView.showTopArticleData(topFeedArticleDataList);
                    }
                }));
    }

    @Override
    public void getBannerData(boolean isShowError) {
        addSubscribe(mDataManager.getBannerData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,
                        WanAndroidApp.getInstance().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onNext(List<BannerData> bannerDataList) {
                        mView.showBannerData(bannerDataList);
                    }
                }));
    }

    @Override
    public void loadMoreData() {
        mCurrentPage++;
        isRefresh = false;
        getFeedArticleList(false);
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        mCurrentPage = 0;
        isRefresh = true;
        getBannerData(isShowError);
        getTopArticleData(isShowError);
        getFeedArticleList(isShowError);
    }

    @Override
    public void attachView(MainPagerContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin) //方法名
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(loginEvent -> mView.showLogoutView()));
    }
}
