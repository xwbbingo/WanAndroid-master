package com.bingo.wanandroid.core.http.api;

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

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * WanAndroid的api
 */
public interface WanAndroidApis {

    String BASEURL = "https://www.wanandroid.com/";

    /**
     * 登录
     * https://www.wanandroid.com/user/login
     * @param username 用户名
     * @param password 用户密码
     * @return 登录数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getLoginData(@Field("username") String username,@Field("password")String password);

    /**
     * 注册
     * https://www.wanandroid.com/user/register
     * @param username 用户名
     * @param password 用户密码
     * @param repassword 确认用户密码
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getRegisterData(@Field("username") String username,@Field("password")String password,@Field("repassword") String repassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     *
     * @return 登陆数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logoutWanAndroid();

    //首页相关
    /**
     * 首页文章列表
     * https://www.wanandroid.com/article/list/0/json
     * @param num 页数
     * @return 列表数据
     */
    @GET("article/list/{num}/json")
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(@Path("num") int num);

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     * @return 常用网站数据
     */
    @GET("friend/json")
    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSiteData();

    /**
     * 热搜
     * http://www.wanandroid.com/hotkey/json
     * @return 热门搜索数据
     */
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<HotSearchData>>> getHotSearchData();

    /**
     * 置顶文章
     * 字段  type 1 0 表示是是否置顶
     * @return 置顶的数据
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<FeedArticleData>>> getTopArticleData();

    /**
     * 搜索文章列表
     * https://www.wanandroid.com/article/query/0/json
     * @param page 页数
     * @param k post 查找的key
     * @return 搜索数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<FeedArticleListData>> getSearchList(@Path("page")int page, @Field("k")String k);

    /**
     * 根据作者昵称搜索文章
     * https://wanandroid.com/article/list/0/json?author=鸿洋
     * @param page 页码
     * @param author 作者, 不支持模糊查询
     * @return 作者文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getSearchByAuthorList(@Path("page")int page,@Query("author")String author);

    //体系
    /**
     * 体系
     * https://www.wanandroid.com/tree/json
     * @return 体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<HierarchyData>>> getHierarchyData();

    /**
     * 知识体系下的文章
     * https://www.wanandroid.com/article/list/0/json?cid=60
     * @param page 页码
     * @param cid  体系二级目录的id
     * @return 体系文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getHierarchyDetailList(@Path("page")int page, @Query("cid")int cid);

    //导航
    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationData>>> getNavigationData();

    //项目
    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 某个分类下的列表数据
     * @param page 页码 从1开始
     * @param cid 分类的id
     * @return 分类项目列表数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectListData>> getProjectList(@Path("page")int page,@Query("cid")int cid);
}
