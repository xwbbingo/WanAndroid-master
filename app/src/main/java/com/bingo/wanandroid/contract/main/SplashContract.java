package com.bingo.wanandroid.contract.main;

import com.bingo.wanandroid.base.presenter.AbstractPresenter;
import com.bingo.wanandroid.base.view.AbstractView;

/**
 * author bingo
 * date 2020/4/27
 */
public interface SplashContract {

    interface View extends AbstractView{

        void jumpToMain();
    }

    interface Presenter extends AbstractPresenter<View>{

        void loadAnimation();
    }
}
