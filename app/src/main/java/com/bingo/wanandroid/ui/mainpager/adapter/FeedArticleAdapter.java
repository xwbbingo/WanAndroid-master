package com.bingo.wanandroid.ui.mainpager.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author bingo
 * date 2020/1/8
 */
public class FeedArticleAdapter extends BaseQuickAdapter<FeedArticleData, FeedArticleAdapter.ArticleViewHolder> {

    public FeedArticleAdapter(int layoutId, List<FeedArticleData> feedArticleDataList) {
        super(layoutId, feedArticleDataList);
    }

    @Override
    protected void convert(ArticleViewHolder helper, FeedArticleData item) {
        //作者为null时,采用分享者为作者
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.mItemArticleCommonAuthor.setText(String.format("%s%s", item.getAuthor(), mContext.getString(R.string.item_article_author)));
        } else if (!TextUtils.isEmpty(item.getShareUser())) {
            helper.mItemArticleCommonAuthor.setText(String.format("%s%s", item.getShareUser(), mContext.getString(R.string.item_article_share_user)));
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.mItemArticleCommonTitle.setText(Html.fromHtml(item.getTitle()));
        }
        if (!TextUtils.isEmpty(item.getChapterName())) {
            String classifyName = item.getSuperChapterName() + "/" + item.getChapterName();
            helper.mItemArticleCommonChapterName.setText(classifyName);
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.mItemArticleCommonNiceDate.setText(item.getNiceDate());
        }
        helper.mItemArticleCommonTop.setVisibility((item.getType() == 0 ? View.GONE : View.VISIBLE));
        setTag(helper, item);
        helper.addOnClickListener(R.id.item_article_common_chapterName);
        helper.addOnClickListener(R.id.item_article_common_collect);
    }

    private void setTag(ArticleViewHolder helper, FeedArticleData item) {
        helper.mItemArticleCommonTag.setVisibility(View.GONE);
        if (item.getNiceDate().contains(mContext.getString(R.string.now))
                ||item.getNiceDate().contains(mContext.getString(R.string.minute))
                || item.getNiceDate().contains(mContext.getString(R.string.hour))) {
            helper.mItemArticleCommonTag.setVisibility(View.VISIBLE);
            helper.mItemArticleCommonTag.setText(R.string.item_article_new);
            helper.mItemArticleCommonTag.setTextColor(ContextCompat.getColor(mContext, R.color.light_red));
            helper.mItemArticleCommonTag.setBackgroundResource(R.drawable.shape_tag_red_background);
        }
    }

    public class ArticleViewHolder extends BaseViewHolder {
        @BindView(R.id.item_article_common_author)
        TextView mItemArticleCommonAuthor;
        @BindView(R.id.item_article_common_niceDate)
        TextView mItemArticleCommonNiceDate;
        @BindView(R.id.item_article_common_title)
        TextView mItemArticleCommonTitle;
        @BindView(R.id.item_article_common_chapterName)
        TextView mItemArticleCommonChapterName;
        @BindView(R.id.item_article_common_collect)
        ImageView mItemArticleCommonCollect;
        @BindView(R.id.item_article_common_group)
        CardView mItemArticleCommonGroup;
        @BindView(R.id.item_article_common_top)
        TextView mItemArticleCommonTop;
        @BindView(R.id.item_article_common_tag)
        TextView mItemArticleCommonTag;

        public ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
