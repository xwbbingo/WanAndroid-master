package com.bingo.wanandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.bingo.wanandroid.R;
import com.bingo.wanandroid.app.WanAndroidApp;
import com.bingo.wanandroid.base.activity.BaseActivity;
import com.bingo.wanandroid.contract.main.SplashContract;
import com.bingo.wanandroid.presenter.main.SplashPresenter;
import com.bingo.wanandroid.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * author bingo
 * date 2020/4/27
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {


    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {
        if (!WanAndroidApp.isFirstRun) {
            jumpToMain();
            return;
        }
        WanAndroidApp.isFirstRun = false;
        StatusBarUtil.immersive(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.loadAnimation();
        startAnimations();
    }

    private void startAnimations() {
        startLottieAnimation(mOneAnimation,"W.json");
        startLottieAnimation(mTwoAnimation,"A.json");
        startLottieAnimation(mThreeAnimation, "N.json");
        startLottieAnimation(mFourAnimation, "A.json");
        startLottieAnimation(mFiveAnimation, "N.json");
        startLottieAnimation(mSixAnimation, "D.json");
        startLottieAnimation(mSevenAnimation, "R.json");
        startLottieAnimation(mEightAnimation, "I.json");
        startLottieAnimation(mNineAnimation, "O.json");
        startLottieAnimation(mTenAnimation, "D.json");
    }

    @Override
    protected void onDestroy() {
        cancelAnimations();
        super.onDestroy();
    }

    private void cancelAnimations() {
        cancelLottieAnimation(mOneAnimation);
        cancelLottieAnimation(mTwoAnimation);
        cancelLottieAnimation(mThreeAnimation);
        cancelLottieAnimation(mFourAnimation);
        cancelLottieAnimation(mFiveAnimation);
        cancelLottieAnimation(mSixAnimation);
        cancelLottieAnimation(mSevenAnimation);
        cancelLottieAnimation(mEightAnimation);
        cancelLottieAnimation(mNineAnimation);
        cancelLottieAnimation(mTenAnimation);
    }

    private void startLottieAnimation(LottieAnimationView lottieAnimationView, String name) {
        lottieAnimationView.setAnimation(name);
        lottieAnimationView.playAnimation();
    }

    private void cancelLottieAnimation(LottieAnimationView lottieAnimationView){
        if (lottieAnimationView != null)
            lottieAnimationView.cancelAnimation();
    }



    @Override
    public void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
