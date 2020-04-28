package com.bingo.wanandroid.presenter.main;

import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.contract.main.SplashContract;
import com.bingo.wanandroid.core.DataManager;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * author bingo
 * date 2020/4/27
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
    }

    @Override
    public void loadAnimation() {
        addSubscribe(Observable.timer(2000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mView.jumpToMain();
            }
        }));
    }
}
