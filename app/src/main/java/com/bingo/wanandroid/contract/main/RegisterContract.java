package com.bingo.wanandroid.contract.main;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;

/**
 * author bingo
 * date 2020/4/8
 */
public interface RegisterContract {

    interface View extends AbstractView{
        /**
         * 显示注册
         */
        void showRegisterSuccess();
    }

    interface Presenter extends AbstractPresenter<View>{
        /**
         * 注册
         * @param username
         * @param password
         * @param rePassword
         */
        void getRegisterData(String username, String password, String rePassword);
    }
}
