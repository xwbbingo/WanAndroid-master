package com.bingo.wanandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.component.RxBus;
import com.bingo.wanandroid.contract.main.MainContract;
import com.bingo.wanandroid.core.event.LoginEvent;
import com.bingo.wanandroid.presenter.main.MainPresenter;
import com.bingo.wanandroid.ui.hierarchy.fragment.HierarchyFragment;
import com.bingo.wanandroid.ui.main.fragment.HotSearchDialogFragment;
import com.bingo.wanandroid.ui.main.fragment.UsefulSiteDialogFragment;
import com.bingo.wanandroid.ui.mainpager.fragment.MainPagerFragment;
import com.bingo.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.bingo.wanandroid.ui.project.fragment.ProjectFragment;
import com.bingo.wanandroid.utils.StatusBarUtil;
import com.bingo.wanandroid.widget.CommonAlertDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主activity，包含多个fragment
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.common_toolbar_title_tv)
    TextView mCommonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar mCommonToolbar;
    @BindView(R.id.fragment_group)
    FrameLayout mFragmentGroup;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mMainFloatingActionBtn;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private int mLastFgIndex;
    private ArrayList<Fragment> mFragments;
    private MainPagerFragment mMainPagerFragment;
    private HierarchyFragment mHierarchyFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;

    private TextView mLogin_tv;
    private MenuItem mMenuItem1;
    private MenuItem mMenuItem2;
    private MenuItem mMenuItem3;
    private MenuItem mMenuItem4;
    private MenuItem mMenuItem5;

    private boolean mAboutType;
    private HotSearchDialogFragment mHotSearchDialogFragment;
    private UsefulSiteDialogFragment mUsefulSiteDialogFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mCommonToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        mCommonToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        mCommonToolbarTitleTv.setText(getString(R.string.home_pager));
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new ArrayList<>();
        if (savedInstanceState == null) {
            initPager(false, Constants.TYPE_MAIN_PAGER);
        } else {

        }
    }

    /**
     * 初始化
     *
     * @param isReCreate
     * @param position
     */
    private void initPager(boolean isReCreate, int position) {
        mPresenter.autoLoginWanAndroid();
        mMainPagerFragment = MainPagerFragment.newInstance(isReCreate, null);
        mFragments.add(mMainPagerFragment);
        initFragments();
        initView();
        switchFragment(position);
    }

    /**
     * 初始化所有的fragment
     */
    private void initFragments() {
        mHierarchyFragment = HierarchyFragment.newInstance(null, null);
        mNavigationFragment = NavigationFragment.newInstance(null, null);
        mProjectFragment = ProjectFragment.newInstance(null, null);
        mFragments.add(mHierarchyFragment);
        mFragments.add(mNavigationFragment);
        mFragments.add(mProjectFragment);
    }

    /**
     * 初始化View
     */
    private void initView() {
        initBottomNavigation();
        initNavigation();
        initDrawerLayout();
    }

    /**
     * 抽屉布局 ok
     */
    private void initDrawerLayout() {
        //通过ActionBarDrawerToggle将toolbar与DrawableLayout关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mCommonToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //可以重新侧滑方法,该方法实现侧滑动画,整个布局移动效果
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View view = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;
                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));
                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                view.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                view.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                view.setScaleX(endScale);
                view.setScaleY(endScale);
            }
        };
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    /**
     * 侧边导航栏
     */
    private void initNavigation() {
        mLogin_tv = mNavView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        mMenuItem1 = mNavView.getMenu().findItem(R.id.nav_item_wan_android);
        mMenuItem2 = mNavView.getMenu().findItem(R.id.nav_item_my_collect);
        mMenuItem3 = mNavView.getMenu().findItem(R.id.nav_item_setting);
        mMenuItem4 = mNavView.getMenu().findItem(R.id.nav_item_about_us);
        mMenuItem5 = mNavView.getMenu().findItem(R.id.nav_item_logout);
        //设置图片为本身的颜色
        mNavView.setItemIconTintList(null);
        //设置item的点击事件
        mNavView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem == mMenuItem1) { // 主页
                startMainPager();
            } else if (menuItem == mMenuItem2) { //收藏
                showSnackBar("功能待开发中...");
            } else if (menuItem == mMenuItem3) { //设置
                startActivity(new Intent(this,SettingActivity.class));
            } else if (menuItem == mMenuItem4) { //关于
                mAboutType = !mAboutType;
                startActivity(new Intent(this,mAboutType?AboutMeActivity.class:AboutUsActivity.class));
            } else if (menuItem == mMenuItem5) { //退出
                logout();
            }
            return true;
        });
    }

    /**/

    private void logout() {
        CommonAlertDialog.newInstance().showDialog(this,
                getString(R.string.logout_tint),
                getString(R.string.ok),
                getString(R.string.no),
                v -> {
                    mPresenter.logoutWanAndroid();
                }, v -> {
                    CommonAlertDialog.newInstance().cancelDialog(true);
                });
    }

    private void startMainPager() {
        mCommonToolbarTitleTv.setText(R.string.home_pager);
        mBottomNavigationView.setVisibility(View.VISIBLE);
        mBottomNavigationView.setSelectedItemId(R.id.tab_main_pager);
        mDrawerLayout.closeDrawers();
    }

    /**
     * 底部导航栏
     */
    private void initBottomNavigation() {
        // BottomNavigationHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.tab_main_pager:
                    mCommonToolbarTitleTv.setText(R.string.home_pager);
                    switchFragment(Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    mCommonToolbarTitleTv.setText(R.string.knowledge_hierarchy);
                    switchFragment(Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_navigation:
                    mCommonToolbarTitleTv.setText(R.string.navigation);
                    switchFragment(Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_project:
                    mCommonToolbarTitleTv.setText(R.string.project);
                    switchFragment(Constants.TYPE_PROJECT);
                    break;
                case R.id.tab_wx_article:

                    break;
            }
            return true;
        });
    }


    /**
     * 切换fragment
     *
     * @param position 下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size())
            return;
        mPresenter.setCurrentPage(position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            //getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usage:
                // 常用网站
                if (mUsefulSiteDialogFragment == null){
                    mUsefulSiteDialogFragment = new UsefulSiteDialogFragment();
                }
                if (!isDestroyed() && mUsefulSiteDialogFragment.isAdded()){
                    mUsefulSiteDialogFragment.dismiss();
                }
                mUsefulSiteDialogFragment.show(getSupportFragmentManager(),"UsefulSiteDialogFragment");
                break;
            case R.id.action_search:
                // 热搜
                if (mHotSearchDialogFragment == null){
                    mHotSearchDialogFragment = new HotSearchDialogFragment();
                }
                if (!isDestroyed() && mHotSearchDialogFragment.isAdded()){
                    mHotSearchDialogFragment.dismiss();
                }
                mHotSearchDialogFragment.show(getSupportFragmentManager(),"HotSearchDialogFragment");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchNavigation() {

    }

    @Override
    public void showAutoLoginSuccess() {
        showSnackBar(getString(R.string.auto_login_success));
        RxBus.getDefault().post(new LoginEvent(true));
    }

    @Override
    public void showAutoLoginFail() {
        RxBus.getDefault().post(new LoginEvent(false));
    }

    /**
     * 退出登录时,自动跳转至登录界面
     */
    @Override
    public void showLogoutSuccess() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void showLoginView() {
        mLogin_tv.setText(mPresenter.getLoginAccount());
        mLogin_tv.setOnClickListener(null);
        mMenuItem5.setVisible(true);
    }

    @Override
    public void showLogoutView() {
        mLogin_tv.setText(R.string.login_in);
        mLogin_tv.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        mMenuItem5.setVisible(false);
    }

    @OnClick({R.id.main_floating_action_btn})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_floating_action_btn:
                jumpToTheTop();
                break;
        }
    }

    private void jumpToTheTop() {
        switch (mPresenter.getCurrentPage()){
            case Constants.TYPE_MAIN_PAGER:
                if (mMainPagerFragment != null)
                    mMainPagerFragment.jumpToTheTop();
                break;
            case Constants.TYPE_KNOWLEDGE:
                if (mHierarchyFragment != null)
                    mHierarchyFragment.jumpToTheTop();
                break;
            case Constants.TYPE_NAVIGATION:
                if (mNavigationFragment != null)
                    mNavigationFragment.jumpToTheTop();
                break;
            case Constants.TYPE_PROJECT:
                if (mProjectFragment != null)
                    mProjectFragment.jumpToTheTop();
                break;
            default:
                break;
        }
    }
}
