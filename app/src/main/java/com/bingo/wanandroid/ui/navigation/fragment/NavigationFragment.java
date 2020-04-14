package com.bingo.wanandroid.ui.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.Constants;
import com.bingo.wanandroid.base.fragment.BaseFragment;
import com.bingo.wanandroid.contract.navigation.NavigationContract;
import com.bingo.wanandroid.core.bean.navigation.NavigationData;
import com.bingo.wanandroid.presenter.navigation.NavigationPresenter;
import com.bingo.wanandroid.ui.navigation.adapter.NavigationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * author bingo
 * date 2020/1/21
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {


    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout mNavigationTabLayout;
    @BindView(R.id.navigation_divider)
    View mNavigationDivider;
    @BindView(R.id.navigation_recycler_view)
    RecyclerView mNavigationRecyclerView;
    @BindView(R.id.refresh_layout)
    LinearLayout mRefreshLayout;
    private NavigationAdapter mNavigationAdapter;

    //org
    private boolean needScroll;
    private boolean isClickTab;

    private int index;
    private LinearLayoutManager mLinearLayoutManager;

    public static NavigationFragment newInstance(String param1,String param2) {

        Bundle args = new Bundle();
        args.putString(Constants.PARAM1,param1);
        args.putString(Constants.PARAM2,param2);
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initView() {
        super.initView();
        List<NavigationData> navigationDataList = new ArrayList<>();
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, navigationDataList);
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        mNavigationRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNavigationRecyclerView.setHasFixedSize(true);
        mNavigationRecyclerView.setAdapter(mNavigationAdapter);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getNavigationData(true);
    }

    @Override
    public void showNavigationData(List<NavigationData> navigationDataList) {
        mNavigationTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationDataList == null?0 : navigationDataList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationDataList.get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity,R.color.shallow_green),
                                ContextCompat.getColor(_mActivity,R.color.shallow_grey)).build();

            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        if (mPresenter.getCurrentPage() == Constants.TYPE_NAVIGATION){
            mRefreshLayout.setVisibility(View.VISIBLE);
        } else {
            mRefreshLayout.setVisibility(View.INVISIBLE);
        }
        if(mNavigationAdapter == null)
            return;
        mNavigationAdapter.replaceData(navigationDataList);
        leftRightLinkage();
    }

    private void leftRightLinkage() {
        mNavigationTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                Log.i("test","position:" + position + "   index:" + index);
                leftLinkageRightSmooth(position);

                //org
//                isClickTab = true;
//                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        mNavigationRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("test","newState:" + newState);
                rightLinkageLeftSmooth(newState);

                //org
//                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
//                    scrollRecyclerView();
//                }
//                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("test","dx:" + dx + "    dy" +dy);

                //org
//                if (needScroll) {
//                    scrollRecyclerView();
//                }
            }
        });
    }

    //根据左边tab点击位置,右边view进行相应移动
    private void leftLinkageRightSmooth(int position) {
        if (index != position){
            index = position;
            mNavigationRecyclerView.stopScroll();
            //此方法不会触发onScrollStateChanged
            mLinearLayoutManager.scrollToPositionWithOffset(position,0);
        }
    }

    //根据右边view滑动,静止时左边tab进行设置
    private void rightLinkageLeftSmooth(int newState) {
        if(newState == RecyclerView.SCROLL_STATE_IDLE){
            int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
            //View view = mLinearLayoutManager.findViewByPosition(firstPosition);
            Log.i("test","first:" + firstPosition + "  index:" + index);
            if (index != firstPosition){
                index = firstPosition;
                mNavigationTabLayout.setTabSelected(index);
            }
        }
    }

    //org
    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mLinearLayoutManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mNavigationRecyclerView.getChildCount()) {
            int top = mNavigationRecyclerView.getChildAt(indexDistance).getTop();
            mNavigationRecyclerView.smoothScrollBy(0, top);
        }
    }

    /**
     * Right recyclerView linkage left tabLayout
     * SCROLL_STATE_IDLE just call once
     *
     * @param newState RecyclerView new scroll state
     */
    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void selectTag(int i) {
        index = i;
        mNavigationRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }

    /**
     * Smooth right to select the position of the left tab
     *
     * @param position checked position
     */
    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (mNavigationTabLayout == null) {
                return;
            }
            mNavigationTabLayout.setTabSelected(index);
        }
        index = position;
    }

    private void smoothScrollToPosition(int position) {
        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        Log.i("test","first:" + firstPosition + "  cur:" + position + "  last:"+ lastPosition);
        if (position <= firstPosition){
            mNavigationRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastPosition){
            int top = mNavigationRecyclerView.getChildAt(position - firstPosition).getTop();
            mNavigationRecyclerView.smoothScrollBy(0,top);
        } else {
            mNavigationRecyclerView.smoothScrollToPosition(position);
            needScroll = true;
        }
    }

    public void jumpToTheTop() {
        if (mNavigationTabLayout != null)
            mNavigationTabLayout.setTabSelected(0);
    }
}
