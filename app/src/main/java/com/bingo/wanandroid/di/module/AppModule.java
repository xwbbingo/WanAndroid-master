package com.bingo.wanandroid.di.module;

import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.core.DataManager;
import com.bingo.wanandroid.core.http.HttpHelper;
import com.bingo.wanandroid.core.http.HttpHelperImpl;
import com.bingo.wanandroid.core.prefs.PreferenceHelper;
import com.bingo.wanandroid.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author bingo
 * date 2020/1/3
 * 依赖提供方
 */
@Module
public class AppModule {

    private final WanAndroidApp application;

    public AppModule(WanAndroidApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    WanAndroidApp provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferenceHelper(PreferenceHelperImpl preferenceHelperImpl) {
        return preferenceHelperImpl;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferenceHelper preferenceHelper) {
        return new DataManager(httpHelper,preferenceHelper);
    }
}
