package com.bingo.wanandroid.base.presenter;


import com.bingo.wanandroid.base.view.AbstractView;

import io.reactivex.disposables.Disposable;

/**
 * author bingo
 * date 2020/1/2
 * MVP模式基类 Presenter
 */
public interface AbstractPresenter<T> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

    /**
     * Set night mode state
     */
    void setNightModeState(boolean nightModeState);

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    boolean getNightModeState();

    /**
     * Set login status
     *
     * @param loginStatus login status
     */
    void setLoginStatus(boolean loginStatus);

    /**
     * Get login status
     *
     * @return if is login status
     */
    boolean getLoginStatus();

    /**
     * Set login account
     *
     * @param account account
     */
    void setLoginAccount(String account);

    /**
     * Get login account
     *
     * @return login account
     */
    String getLoginAccount();

    /**
     * Set login password
     *
     * @param password password
     */
    void setLoginPassword(String password);

    /**
     * Get login password
     *
     */
    String getLoginPassword();

    /**
     * Set current page
     *
     */
    void setCurrentPage(int position);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();


    /**
     * Set auto cache state
     * @param b current auto cache state
     */
    void setAutoCacheState(boolean b);

    /**
     * Get auto cache state
     * @return if auto cache state
     */
    boolean getAutoCacheState();

    /**
     * Set no image state
     * @param b current no image state
     */
    void setNoImageState(boolean b);

    /**
     * Get no image state
     * @return if has image state
     */
    boolean getNoImageState();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);
}
