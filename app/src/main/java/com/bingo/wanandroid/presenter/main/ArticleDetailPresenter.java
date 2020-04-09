package com.bingo.wanandroid.presenter.main;

import com.bingo.wanandroid.base.presenter.BasePresenter;
import com.bingo.wanandroid.contract.main.ArticleDetailContact;
import com.bingo.wanandroid.core.DataManager;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/2/9
 */
public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContact.View> implements ArticleDetailContact.Presenter {

    @Inject
    public ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(ArticleDetailContact.View view) {
        super.attachView(view);
    }
}
