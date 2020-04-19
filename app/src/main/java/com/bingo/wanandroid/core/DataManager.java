package com.bingo.wanandroid.core;

import com.bingo.wanandroid.core.bean.BaseResponse;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;
import com.bingo.wanandroid.core.bean.mainpager.banner.BannerData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleListData;
import com.bingo.wanandroid.core.bean.main.login.LoginData;
import com.bingo.wanandroid.core.bean.main.search.HotSearchData;
import com.bingo.wanandroid.core.bean.main.search.UsefulSiteData;
import com.bingo.wanandroid.core.bean.navigation.NavigationData;
import com.bingo.wanandroid.core.bean.project.ProjectClassifyData;
import com.bingo.wanandroid.core.bean.project.ProjectListData;
import com.bingo.wanandroid.core.http.HttpHelper;
import com.bingo.wanandroid.core.prefs.PreferenceHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * author bingo
 * date 2020/1/3
 */
public class DataManager implements HttpHelper, PreferenceHelper {

    private HttpHelper mHttpHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(HttpHelper httpHelper, PreferenceHelper preferenceHelper) {
        this.mHttpHelper = httpHelper;
        this.mPreferenceHelper = preferenceHelper;
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mHttpHelper.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mHttpHelper.getRegisterData(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logoutWanAndroid() {
        return mHttpHelper.logoutWanAndroid();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int num) {
        return mHttpHelper.getFeedArticleList(num);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSiteData() {
        return mHttpHelper.getUsefulSiteData();
    }

    @Override
    public Observable<BaseResponse<List<HotSearchData>>> getHotSearchData() {
        return mHttpHelper.getHotSearchData();
    }

    @Override
    public Observable<BaseResponse<List<FeedArticleData>>> getTopArticleData() {
        return mHttpHelper.getTopArticleData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int page, String k) {
        return mHttpHelper.getSearchList(page, k);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchByAuthorList(int page, String author) {
        return mHttpHelper.getSearchByAuthorList(page,author);
    }

    @Override
    public Observable<BaseResponse<List<HierarchyData>>> getHierarchyData() {
        return mHttpHelper.getHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getHierarchyDetailList(int page, int cid) {
        return mHttpHelper.getHierarchyDetailList(page,cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationData>>> getNavigationData() {
        return mHttpHelper.getNavigationData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mHttpHelper.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectList(int page, int cid) {
        return mHttpHelper.getProjectList(page,cid);
    }

    @Override
    public void setNightModeState(boolean nightModeState) {
        mPreferenceHelper.setNightModeState(nightModeState);
    }

    @Override
    public boolean getNightModeState() {
        return mPreferenceHelper.getNightModeState();
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mPreferenceHelper.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginPassword() {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferenceHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferenceHelper.setAutoCacheState(b);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferenceHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferenceHelper.setNoImageState(b);
    }
}
