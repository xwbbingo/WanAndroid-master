package com.bingo.wanandroid.presenter.main;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxBus;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.main.MainContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.BaseResponse;
import com.bingo.wanandroid.core.bean.main.login.LoginData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;
import com.bingo.wanandroid.core.bean.mainpager.banner.BannerData;
import com.bingo.wanandroid.core.event.LoginEvent;
import com.bingo.wanandroid.presenter.mainpager.MainPagerPresenter;
import com.bingo.wanandroid.utils.CommonUtil;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * author bingo
 * date 2020/1/2
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void autoLoginWanAndroid() {
        Observable<BaseResponse<LoginData>> loginDataObservable = mDataManager.getLoginData(getLoginAccount(), getLoginPassword());
        addSubscribe(loginDataObservable
                .map(new Function<BaseResponse<LoginData>, HashMap<String,Object>>() {
                    @Override
                    public HashMap<String, Object> apply(BaseResponse<LoginData> loginDataBaseResponse) throws Exception {
                        return createLoginResponseMap(loginDataBaseResponse);
                    }
                })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {
                        BaseResponse<LoginData> loginResponse = CommonUtil.cast(map.get(Constants.LOGIN_DATA));
                        Log.i("test"," 222 " + loginResponse.getErrorCode());
                        if (loginResponse.getErrorCode() == BaseResponse.SUCCESS){
                            LoginData loginData = loginResponse.getData();
                            setLoginAccount(loginData.getUsername());
                            setLoginPassword(loginData.getPassword());
                            setLoginStatus(true);
                            mView.showAutoLoginSuccess();
                        } else {
                            setLoginStatus(false);
                            mView.showAutoLoginFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showAutoLoginFail();
                    }
                }));
    }


    private HashMap<String, Object> createLoginResponseMap(BaseResponse<LoginData> loginDataBaseResponse) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.LOGIN_DATA, loginDataBaseResponse);
        return map;
    }

    @Override
    public void logoutWanAndroid() {
        addSubscribe(mDataManager.logoutWanAndroid()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleLogoutResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.logout_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        setLoginAccount("");
                        setLoginPassword("");
                        setLoginStatus(false);
                        RxBus.getDefault().post(new LoginEvent(false));
                        mView.showLogoutSuccess();
                    }
                }));
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        mView.showLoginView();
                    }
                }));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent logoutEvent) throws Exception {
                        mView.showLogoutView();
                    }
                }));

    }
}
