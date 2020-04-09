package com.bingo.wanandroid.contract.main;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;

/**
 * author bingo
 * date 2020/1/9
 */
public interface LoginContact {

    interface View extends AbstractView{

        void showLoginSuccess();
    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 登录数据
         * @param username 用户名
         * @param password 用户密码
         */
        void getLoginData(String username,String password);
    }
}
