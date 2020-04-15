package com.bingo.wanandroid.ui.navigation.adapter;

import android.app.ActivityOptions;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.component.JudgeUtils;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.core.bean.navigation.NavigationData;
import com.bingo.wanandroid.utils.ColorUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author bingo
 * date 2020/1/17
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationData, NavigationAdapter.NavigationViewHolder> {


    public NavigationAdapter(int layoutId, List<NavigationData> navigationDataList) {
        super(layoutId, navigationDataList);
    }

    @Override
    protected void convert(NavigationViewHolder helper, NavigationData item) {
        if (!TextUtils.isEmpty(item.getName())){
            helper.mItemNavigationTitle.setText(item.getName());
            helper.mItemNavigationTitle.setTextColor(ColorUtil.randomColor());
        }
        List<FeedArticleData> articles = item.getArticles();
        helper.mItemNavigationFlowLayout.setAdapter(new TagAdapter<FeedArticleData>(articles) {
            @Override
            public View getView(FlowLayout parent, int position, FeedArticleData feedArticleData) {
                TextView flowTv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        helper.mItemNavigationFlowLayout, false);
                if (feedArticleData == null)
                    return null;
                String title = feedArticleData.getTitle();
                flowTv.setTextColor(ColorUtil.randomColor());
                flowTv.setText(title);
                return flowTv;
            }
        });
        helper.mItemNavigationFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // TODO: 2020/1/21 跳转至网站界面
                startNavigationDetailPager(view,position,parent,articles);
                return true;
            }
        });
    }

    private void startNavigationDetailPager(View view, int position, FlowLayout parent, List<FeedArticleData> articles) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth() / 2,
                view.getHeight() / 2,
                0,
                0);
        JudgeUtils.startArticleDetailActivity(parent.getContext(),
                compat,
                articles.get(position).getId(),
                articles.get(position).getTitle(),
                articles.get(position).getLink());
    }

    public class NavigationViewHolder extends BaseViewHolder {

        @BindView(R.id.item_navigation_title)
        TextView mItemNavigationTitle;
        @BindView(R.id.item_navigation_flow_layout)
        TagFlowLayout mItemNavigationFlowLayout;

        public NavigationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
