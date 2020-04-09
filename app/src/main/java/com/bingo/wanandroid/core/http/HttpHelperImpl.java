package com.bingo.wanandroid.core.http;

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
import com.bingo.wanandroid.core.http.api.WanAndroidApis;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * author bingo
 * date 2020/1/3
 */
public class HttpHelperImpl implements HttpHelper {

    private WanAndroidApis mWanAndroidApis;

    @Inject
    public HttpHelperImpl(WanAndroidApis mWanAndroidApis) {
        this.mWanAndroidApis = mWanAndroidApis;
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mWanAndroidApis.getLoginData(username,password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mWanAndroidApis.getRegisterData(username,password,repassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logoutWanAndroid() {
        return mWanAndroidApis.logoutWanAndroid();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int num) {
        return mWanAndroidApis.getFeedArticleList(num);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mWanAndroidApis.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSitesData() {
        return mWanAndroidApis.getUsefulSitesData();
    }

    @Override
    public Observable<BaseResponse<List<HotSearchData>>> getHotSearchData() {
        return mWanAndroidApis.getHotSearchData();
    }

    @Override
    public Observable<BaseResponse<List<FeedArticleData>>> getTopArticleData() {
        return mWanAndroidApis.getTopArticleData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int page, String k) {
        return mWanAndroidApis.getSearchList(page,k);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchByAuthorList(int page, String author) {
        return mWanAndroidApis.getSearchByAuthorList(page,author);
    }

    @Override
    public Observable<BaseResponse<List<HierarchyData>>> getHierarchyData() {
        return mWanAndroidApis.getHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getHierarchyDetailList(int page, int cid) {
        return mWanAndroidApis.getHierarchyDetailList(page,cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationData>>> getNavigationData() {
        return mWanAndroidApis.getNavigationData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mWanAndroidApis.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectList(int page, int cid) {
        return mWanAndroidApis.getProjectList(page,cid);
    }
}
