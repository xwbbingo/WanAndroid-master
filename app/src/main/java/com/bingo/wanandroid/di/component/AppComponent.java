package com.bingo.wanandroid.di.component;

import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.di.module.AbstractAllActivityModule;
import com.bingo.wanandroid.di.module.AbstractAllDialogFragmentModule;
import com.bingo.wanandroid.di.module.AbstractAllFragmentModule;
import com.bingo.wanandroid.di.module.AppModule;
import com.bingo.wanandroid.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * java.lang.IllegalArgumentException: No injector factory bound for Class
 * author bingo
 * date 2020/1/5
 * 注入器
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AbstractAllDialogFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    /**
     * 注入WanAndroid实例
     * @param wanAndroidApp 实例
     */
    void inject(WanAndroidApp wanAndroidApp);

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    WanAndroidApp getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    DataManager getDataManager();
}
