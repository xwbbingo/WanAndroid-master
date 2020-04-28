package com.bingo.wanandroid.app;

import android.app.Activity;
import android.app.Application;

import com.bingo.wanandroid.di.component.AppComponent;
import com.bingo.wanandroid.di.component.DaggerAppComponent;
import com.bingo.wanandroid.di.module.AppModule;
import com.bingo.wanandroid.di.module.HttpModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * author bingo
 * date 2020/1/2
 */
public class WanAndroidApp extends Application implements HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> mDispatchingAndroidInjector;

    private static WanAndroidApp instance;
    private static volatile AppComponent appComponent;
    public static boolean isFirstRun = true;

    public static synchronized WanAndroidApp getInstance() {
        return instance;
    }

    public static synchronized AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .httpModule(new HttpModule())
                .build();
        appComponent.inject(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingAndroidInjector;
    }
}
