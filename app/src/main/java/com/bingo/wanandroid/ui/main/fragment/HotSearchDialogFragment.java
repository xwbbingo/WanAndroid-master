package com.bingo.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.base.fragment.BaseDialogFragment;
import com.bingo.wanandroid.component.JudgeUtils;
import com.bingo.wanandroid.contract.main.HotSearchContract;
import com.bingo.wanandroid.core.bean.main.search.HotSearchData;
import com.bingo.wanandroid.presenter.main.HotSearchPresenter;
import com.bingo.wanandroid.utils.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author bingo
 * date 2020/4/11
 */
public class HotSearchDialogFragment extends BaseDialogFragment<HotSearchPresenter> implements HotSearchContract.View {


    @BindView(R.id.hot_search_tv)
    TextView mHotSearchTv;
    @BindView(R.id.hot_search_edit)
    EditText mHotSearchEdit;
    @BindView(R.id.hot_search_flow_layout)
    TagFlowLayout mHotSearchFlowLayout;
    @BindView(R.id.hot_search_scroll_view)
    NestedScrollView mHotSearchScrollView;
    @BindView(R.id.hot_search_floating_btn)
    FloatingActionButton mHotSearchFloatingBtn;
    @BindView(R.id.hot_search_toolbar)
    Toolbar mHotSearchToolbar;

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
        return R.layout.fragment_dialog_hot_search;
    }

    @Override
    protected void initEventAndData() {
        mHotSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mHotSearchEdit.getText().toString())) {
                    mHotSearchEdit.setHint(R.string.search_tint);
                } else {
                    mHotSearchEdit.setHint("");
                }
            }
        });
        mHotSearchToolbar.setNavigationOnClickListener(v -> {
            if (isVisible())
                dismiss();
        });
        mPresenter.getHotSearchData();
    }

    @Override
    public void showHotSearchData(List<HotSearchData> hotSearchDataList) {

        mHotSearchFlowLayout.setAdapter(new TagAdapter<HotSearchData>(hotSearchDataList) {
            @Override
            public View getView(FlowLayout parent, int position, HotSearchData hotSearchData) {

                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.flow_layout_tv, parent, false);
                String name = hotSearchData.getName();
                tv.setText(name);
                tv.setBackgroundColor(ColorUtil.randomTagColor());
                tv.setTextColor(ColorUtil.randomColor());
                return tv;
            }
        });
        mHotSearchFlowLayout.setOnTagClickListener((view, position, parent) -> {
            JudgeUtils.startSearchListActivity(getActivity(), hotSearchDataList.get(position).getName().trim());
            return false;
        });
    }

    @OnClick({R.id.hot_search_tv, R.id.hot_search_floating_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_search_tv:
                // TODO: 2020/4/11 点击搜索后,需将记录添加到历史中,
                JudgeUtils.startSearchListActivity(getActivity(), mHotSearchEdit.getText().toString().trim());
                break;
            case R.id.hot_search_floating_btn:
                mHotSearchScrollView.smoothScrollTo(0, 0);
                break;
        }
    }

}
