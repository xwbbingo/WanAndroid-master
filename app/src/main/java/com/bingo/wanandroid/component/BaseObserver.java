package com.bingo.wanandroid.component;

import android.text.TextUtils;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.core.http.exception.ServerException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * author bingo
 * date 2020/1/5
 * 订阅者
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private AbstractView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(AbstractView view){
        this.mView = view;
    }

    protected BaseObserver(AbstractView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(AbstractView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(AbstractView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(WanAndroidApp.getInstance().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(WanAndroidApp.getInstance().getString(R.string.unKnown_error));
        }
        if (isShowError) {
            mView.showError();
        }
    }
}
