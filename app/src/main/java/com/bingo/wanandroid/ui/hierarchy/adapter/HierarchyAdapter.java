package com.bingo.wanandroid.ui.hierarchy.adapter;

import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.core.bean.hierarchy.HierarchyData;
import com.bingo.wanandroid.utils.ColorUtil;
import com.bingo.wanandroid.utils.CommonUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author bingo
 * date 2020/1/17
 */
public class HierarchyAdapter extends BaseQuickAdapter<HierarchyData, HierarchyAdapter.HierarchyDataViewHolder> {


    public HierarchyAdapter(int layoutId, List<HierarchyData> hierarchyDataList) {
        super(layoutId, hierarchyDataList);
    }

    @Override
    protected void convert(HierarchyDataViewHolder helper, HierarchyData item) {
        if (item.getName() == null)
            return;
        helper.mItemHierarchyTitle.setText(item.getName());
        helper.mItemHierarchyTitle.setTextColor(ColorUtil.randomColor());
        if (item.getChildren() == null)
            return;
        StringBuilder content = new StringBuilder();
        for (HierarchyData.HierarchyChildren hierarchyChildren : item.getChildren()) {
            content.append(hierarchyChildren.getName()).append("  ");
        }
        helper.mItemHierarchyContent.setText(content.toString());
    }

    public class HierarchyDataViewHolder extends BaseViewHolder {
        @BindView(R.id.item_hierarchy_title)
        TextView mItemHierarchyTitle;
        @BindView(R.id.item_hierarchy_content)
        TextView mItemHierarchyContent;

        public HierarchyDataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
