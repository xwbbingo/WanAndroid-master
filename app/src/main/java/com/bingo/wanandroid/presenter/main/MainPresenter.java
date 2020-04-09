package com.bingo.wanandroid.presenter.main;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxBus;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.main.MainContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.main.login.LoginData;
import com.bingo.wanandroid.core.event.LoginEvent;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

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
