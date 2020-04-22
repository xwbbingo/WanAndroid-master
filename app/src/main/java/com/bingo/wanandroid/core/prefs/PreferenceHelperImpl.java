package com.bingo.wanandroid.core.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.app.WanAndroidApp;

import javax.inject.Inject;

/**
 * author bingo
 * date 2020/1/6
 */
public class PreferenceHelperImpl implements PreferenceHelper {

    private final SharedPreferences mPreferences;

    @Inject
    public PreferenceHelperImpl() {
        mPreferences = WanAndroidApp.getInstance().getSharedPreferences(Constants.WAN_ANDROID_SP, Context.MODE_PRIVATE);
    }

    @Override
    public void setNightModeState(boolean nightModeState) {
        mPreferences.edit().putBoolean(Constants.NIGHT_MODE_STATE,nightModeState).apply();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferences.getBoolean(Constants.NIGHT_MODE_STATE,false);
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mPreferences.edit().putBoolean(Constants.LOGIN_STATUS,loginStatus).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferences.getBoolean(Constants.LOGIN_STATUS,false);
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferences.edit().putString(Constants.LOGIN_ACCOUNT,account).apply();
    }

    @Override
    public String getLoginAccount() {
        return mPreferences.getString(Constants.LOGIN_ACCOUNT,"");
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(Constants.LOGIN_PASSWORD,password).apply();
    }

    @Override
    public String getLoginPassword() {
        return mPreferences.getString(Constants.LOGIN_PASSWORD,"");
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferences.edit().putInt(Constants.CURRENT_PAGE,position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mPreferences.getInt(Constants.CURRENT_PAGE,0);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferences.getBoolean(Constants.AUTO_CACHE_STATE,true);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferences.edit().putBoolean(Constants.AUTO_CACHE_STATE,b).apply();
    }

    @Override
    public boolean getNoImageState() {
        return mPreferences.getBoolean(Constants.NO_IMAGE_STATE,false);
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferences.edit().putBoolean(Constants.NO_IMAGE_STATE,b).apply();
    }
}
