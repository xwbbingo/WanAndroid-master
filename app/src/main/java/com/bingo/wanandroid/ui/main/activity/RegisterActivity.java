package com.bingo.wanandroid.ui.main.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.contract.main.RegisterContract;
import com.bingo.wanandroid.presenter.main.RegisterPresenter;
import com.bingo.wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author bingo
 * date 2020/4/8
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.register_toolbar)
    Toolbar mRegisterToolbar;
    @BindView(R.id.register_toolbar_title_tv)
    TextView mRegisterToolbarTitleTv;
    @BindView(R.id.register_password_edit)
    EditText mRegisterPasswordEdit;
    @BindView(R.id.register_account_edit)
    EditText mRegisterAccountEdit;
    @BindView(R.id.register_confirm_password_edit)
    EditText mRegisterConfirmPasswordEdit;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mRegisterToolbar);
        mRegisterToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.register_bac));
        mRegisterToolbarTitleTv.setText(R.string.register);
        mRegisterToolbarTitleTv.setTextColor(ContextCompat.getColor(this, R.color.white));
        mRegisterToolbarTitleTv.setTextSize(20);
        mRegisterToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            mRegisterAccountEdit.requestFocus();
            inputMethodManager.showSoftInput(mRegisterAccountEdit, 0);
        }
//        mPresenter.addRxBindingSubscribe(RxView.clicks(mRegisterBtn)
//                .throttleFirst(Constants.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
//                .filter(o -> mPresenter != null)
//                .subscribe(o -> register()));
    }

    private void register() {
        mPresenter.getRegisterData(mRegisterAccountEdit.getText().toString().trim(),
                mRegisterPasswordEdit.getText().toString().trim(),
                mRegisterConfirmPasswordEdit.getText().toString().trim());
    }

    @Override
    public void showRegisterSuccess() {
        showSnackBar(getString(R.string.register_success));
        onBackPressedSupport();
    }

    @OnClick({R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.register_btn:
                register();
                break;
        }
    }
}
