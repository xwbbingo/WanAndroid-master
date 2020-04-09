package com.bingo.wanandroid.ui.project.adapter;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.core.bean.mainpager.article.FeedArticleData;
import com.bingo.wanandroid.utils.TestUtil;
import com.bingo.wanandroid.widget.GlideImageLoader;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author bingo
 * date 2020/1/8
 */
public class ProjectListAdapter extends BaseQuickAdapter<FeedArticleData, ProjectListAdapter.ProjectListViewHolder> {


    public ProjectListAdapter(int layoutId, List<FeedArticleData> feedArticleDataList) {
        super(layoutId, feedArticleDataList);
    }

    @Override
    protected void convert(ProjectListViewHolder helper, FeedArticleData item) {
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            // TODO: 2020/2/3 无图模式下不加载图片
            Glide.with(mContext).load(item.getEnvelopePic()).into(helper.mItemProjectListImage);
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
           helper.mItemProjectListTitle.setText(item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getDesc())) {
           helper.mItemProjectListContent.setText(item.getDesc());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
           helper.mItemProjectListTime.setText(item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getAuthor())){
            helper.mItemProjectListAuthor.setText(item.getAuthor());
        }
        if(!TextUtils.isEmpty(item.getApkLink())){
            helper.mItemProjectListInstall.setVisibility(View.VISIBLE);
        } else {
            helper.mItemProjectListInstall.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.item_project_list_install);
    }

    public class ProjectListViewHolder extends BaseViewHolder {

        @BindView(R.id.item_project_list_image)
        ImageView mItemProjectListImage;
        @BindView(R.id.item_project_list_title)
        TextView mItemProjectListTitle;
        @BindView(R.id.item_project_list_content)
        TextView mItemProjectListContent;
        @BindView(R.id.item_project_list_time)
        TextView mItemProjectListTime;
        @BindView(R.id.item_project_list_author)
        TextView mItemProjectListAuthor;
        @BindView(R.id.item_project_list_install)
        TextView mItemProjectListInstall;

        public ProjectListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
