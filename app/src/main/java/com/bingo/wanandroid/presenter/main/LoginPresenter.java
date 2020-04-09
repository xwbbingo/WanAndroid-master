package com.bingo.wanandroid.presenter.main;

import android.text.TextUtils;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxBus;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.main.LoginContact;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.main.login.LoginData;
import com.bingo.wanandroid.core.event.LoginEvent;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/9
 */
public class LoginPresenter extends BasePresenter<LoginContact.View> implements LoginContact.Presenter {

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getLoginData(String username, String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            mView.showSnackBar(WanAndroidApp.getInstance().getString(R.string.account_password_null_tint));
            return;
        }
        addSubscribe(mDataManager.getLoginData(username, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.login_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        setLoginAccount(loginData.getUsername());
                        setLoginPassword(loginData.getPassword());
                        setLoginStatus(true);
                        RxBus.getDefault().post(new LoginEvent(true));
                        mView.showLoginSuccess();
                    }
                }));
    }

    @Override
    public void attachView(LoginContact.View view) {
        super.attachView(view);
    }
}
