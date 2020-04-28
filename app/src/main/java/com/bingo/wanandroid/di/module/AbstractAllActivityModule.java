package com.bingo.wanandroid.di.module;

import com.bingo.wanandroid.di.component.BaseActivityComponent;
import com.bingo.wanandroid.ui.hierarchy.activity.HierarchyDetailActivity;
import com.bingo.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.bingo.wanandroid.ui.main.activity.LoginActivity;
import com.bingo.wanandroid.ui.main.activity.MainActivity;
import com.bingo.wanandroid.ui.main.activity.RegisterActivity;
import com.bingo.wanandroid.ui.main.activity.SettingActivity;
import com.bingo.wanandroid.ui.main.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * author bingo
 * date 2020/1/5
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributesSplashActivityInjector();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity contributesRegisterActivityInjector();

    @ContributesAndroidInjector(modules = HierarchyDetailActivityModule.class)
    abstract HierarchyDetailActivity contributesHierarchyDetailActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();

    @ContributesAndroidInjector(modules = SettingActivityModule.class)
    abstract SettingActivity contributeSettingActivityInjector();

}
