package com.bingo.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.base.fragment.BaseDialogFragment;
import com.bingo.wanandroid.component.JudgeUtils;
import com.bingo.wanandroid.contract.main.UsefulSitesContract;
import com.bingo.wanandroid.core.bean.main.search.UsefulSiteData;
import com.bingo.wanandroid.presenter.main.UsefulSitePresenter;
import com.bingo.wanandroid.utils.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
/**
 * author bingo
 * date 2020/4/15
 */
public class UsefulSiteDialogFragment extends BaseDialogFragment<UsefulSitePresenter> implements UsefulSitesContract.View {

    @BindView(R.id.useful_site_toolbar)
    Toolbar mUsefulSiteToolbar;
    @BindView(R.id.useful_site_flow_layout)
    TagFlowLayout mUsefulSiteFlowLayout;
    @BindView(R.id.useful_site_scroll_view)
    NestedScrollView mUsefulSiteScrollView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
        //initDialog()
        //java.lang.NullPointerException:Attempt to invoke virtual method 'android.view.Window android.app.Dialog.getWindow()'
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = (int) (displayMetrics.widthPixels * 0.98);
        assert window != null;
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_useful_site;
    }

    @Override
    protected void initEventAndData() {
        initToolbar();
        mUsefulSiteToolbar.setNavigationOnClickListener(v -> {
            if (isVisible())
                dismiss();
        });
        mPresenter.getUsefulSiteData();
    }

    private void initToolbar() {
        mUsefulSiteToolbar.setTitle(R.string.useful_sites);
    }

    @Override
    public void showUsefulSiteData(List<UsefulSiteData> usefulSiteDataList) {
        mUsefulSiteFlowLayout.setAdapter(new TagAdapter<UsefulSiteData>(usefulSiteDataList) {
            @Override
            public View getView(FlowLayout parent, int position, UsefulSiteData usefulSiteData) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.flow_layout_tv,parent,false);
                String name = usefulSiteData.getName();
                tv.setText(name);
                tv.setBackgroundColor(ColorUtil.randomTagColor());
                tv.setTextColor(ColorUtil.randomColor());
                return tv;
            }
        });

        mUsefulSiteFlowLayout.setOnTagClickListener((view, position, parent) -> {
            UsefulSiteData usefulSiteData = usefulSiteDataList.get(position);
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    Objects.requireNonNull(getActivity()), view, getString(R.string.share_view));
            JudgeUtils.startArticleDetailActivity(getActivity(),
                    compat,
                    usefulSiteData.getId(),
                    usefulSiteData.getName(),
                    usefulSiteData.getLink());
            return true;
        });
    }


}
