package com.bingo.wanandroid.presenter.main;

import android.text.TextUtils;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.component.BaseObserver;
import com.bingo.wanandroid.component.RxUtils;
import com.bingo.wanandroid.contract.main.RegisterContract;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.bean.main.login.LoginData;

import javax.inject.Inject;

import io.reactivex.functions.Predicate;

/**
 * author bingo
 * date 2020/4/8
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getRegisterData(String username, String password, String rePassword) {
        if (TextUtils.isEmpty(username)
                || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(rePassword)) {
            mView.showSnackBar(WanAndroidApp.getInstance().getString(R.string.account_password_null_tint));
            return;
        }
        if (!rePassword.equals(password)) {
            mView.showSnackBar(WanAndroidApp.getInstance().getString(R.string.password_not_same));
            return;
        }
        addSubscribe(mDataManager.getRegisterData(username, password, rePassword)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(new Predicate<LoginData>() {
                    @Override
                    public boolean test(LoginData loginData) throws Exception {
                        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(rePassword);
                    }
                }).subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getInstance().getString(R.string.register_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        mView.showRegisterSuccess();
                    }
                }));
    }

    @Override
    public void attachView(RegisterContract.View view) {
        super.attachView(view);
    }
}
