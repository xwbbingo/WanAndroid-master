package com.bingo.wanandroid.ui.main.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bingo.wanandroid.R;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.contract.main.LoginContract;
import com.bingo.wanandroid.presenter.main.LoginPresenter;
import com.bingo.wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author bingo
 * date 2020/1/14
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.login_toolbar)
    Toolbar mLoginToolbar;
    @BindView(R.id.login_account_edit)
    EditText mLoginAccountEdit;
    @BindView(R.id.login_password_edit)
    EditText mLoginPasswordEdit;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_register_btn)
    Button mLoginRegisterBtn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mLoginToolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mLoginToolbar);
        mLoginToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showLoginSuccess() {
        showToast(getString(R.string.login_success));
        onBackPressedSupport();
    }

    @OnClick({R.id.login_btn,R.id.login_register_btn})
    void onViewClicked(View v){
        switch (v.getId()){
            case R.id.login_btn:
                startLoginEvent();
                break;
            case R.id.login_register_btn:
                startRegisterEvent();
                break;
        }
    }

    private void startRegisterEvent() {
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(mLoginRegisterBtn,
                mLoginRegisterBtn.getWidth()/2,
                mLoginRegisterBtn.getHeight()/2,
                0,
                0);
        startActivity(new Intent(this,RegisterActivity.class),options.toBundle());
    }

    private void startLoginEvent() {
        mPresenter.getLoginData(mLoginAccountEdit.getText().toString(),
                mLoginPasswordEdit.getText().toString());
    }

}
