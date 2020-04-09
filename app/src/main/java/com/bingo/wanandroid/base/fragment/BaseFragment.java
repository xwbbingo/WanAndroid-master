package com.bingo.wanandroid.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.utils.CommonUtil;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * author bingo
 * date 2020/1/3
 */
public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractFragment implements AbstractView {

    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }

    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtil.showMessage(_mActivity,errorMsg);
    }

    @Override
    public void showToast(String message) {
        CommonUtil.showMessage(_mActivity,message);
    }

    @Override
    public void showSnackBar(String message) {
        CommonUtil.showSnackMessage(_mActivity,message);
    }
}
