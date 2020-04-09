package com.bingo.wanandroid.contract.main;


import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;

/**
 * author bingo
 * date 2020/1/2
 */

public interface MainContract {

    interface View extends AbstractView {

        /**
         * Show switch project
         */
        void showSwitchProject();

        /**
         * Show switch navigation
         */
        void showSwitchNavigation();

        /**
         * Show logout success
         */
        void showLogoutSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * logout
         */
        void logoutWanAndroid();
    }

}
