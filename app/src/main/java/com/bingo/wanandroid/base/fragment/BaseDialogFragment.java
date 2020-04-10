package com.bingo.wanandroid.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;
import com.bingo.wanandroid.utils.CommonUtil;

import javax.inject.Inject;

import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * author bingo
 * date 2020/4/10
 */
public abstract class BaseDialogFragment<T extends AbstractPresenter> extends AbstractDialogFragment implements AbstractView {

    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
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
        if (getActivity() == null)
            return;
        CommonUtil.showSnackMessage(getActivity(),errorMsg);
    }

    @Override
    public void showToast(String message) {
        if (getActivity() == null)
            return;
        CommonUtil.showMessage(getActivity(),message);
    }

    @Override
    public void showSnackBar(String message) {
        if (getActivity() == null)
            return;
        CommonUtil.showSnackMessage(getActivity(),message);
    }
}
