package com.bingo.wanandroid.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bingo.wanandroid.R;

/**
 * author bingo
 * date 2020/1/15
 */
public class CommonAlertDialog {

    private AlertDialog mAlertDialog;

    public static CommonAlertDialog newInstance() {
        return CommonAlertDialogHolder.COMMON_ALERT_DIALOG;
    }

    private static class CommonAlertDialogHolder{
        private static final CommonAlertDialog COMMON_ALERT_DIALOG = new CommonAlertDialog();
    }

    /**
     * Cancel alertDialog
     */
    public void cancelDialog(boolean isAdd) {
        if (isAdd && mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    /**
     * Show alertDialog
     *
     * @param activity activity instance
     * @param content show content
     * @param btnContent btn content
     */
    public void showDialog(Activity activity,String content,String btnContent){
        if (activity == null)
            return;
        if (mAlertDialog == null)
            mAlertDialog = new AlertDialog.Builder(activity, R.style.myCorDialog).create();
        if (!mAlertDialog.isShowing())
            mAlertDialog.show();
        mAlertDialog.setCanceledOnTouchOutside(false);
        Window window = mAlertDialog.getWindow();
        if (window !=null){
            window.setContentView(R.layout.common_alert_dialog);
            TextView contentTv = window.findViewById(R.id.dialog_content);
            contentTv.setText(content);
            Button okBtn = window.findViewById(R.id.dialog_btn);
            okBtn.setText(btnContent);
            okBtn.setOnClickListener(v -> {
                if (mAlertDialog != null){
                    mAlertDialog.cancel();
                    mAlertDialog = null;
                }
            });
            //设置分割线和取消按键隐藏
            View btnDivider = window.findViewById(R.id.dialog_btn_divider);
            btnDivider.setVisibility(View.GONE);
            Button mNeBtn = window.findViewById(R.id.dialog_negative_btn);
            mNeBtn.setVisibility(View.GONE);
        }
    }

    /**
     * Show alertDialog
     *
     * @param activity activity instance
     * @param content show content
     * @param btnContent btn content
     * @param onClickListener btn onClickListener
     */
    public void showDialog(Activity activity, String content, String btnContent, final View.OnClickListener onClickListener) {
        if (activity == null) {
            return;
        }
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(activity, R.style.myCorDialog).create();
        }
        if (!mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
        mAlertDialog.setCanceledOnTouchOutside(false);
        Window window = mAlertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.common_alert_dialog);
            TextView contentTv = (TextView) window.findViewById(R.id.dialog_content);
            contentTv.setText(content);
            Button mOkBtn = (Button) window.findViewById(R.id.dialog_btn);
            mOkBtn.setText(btnContent);
            mOkBtn.setOnClickListener(onClickListener);
            View btnDivider = window.findViewById(R.id.dialog_btn_divider);
            btnDivider.setVisibility(View.GONE);
            Button mNeBtn = (Button) window.findViewById(R.id.dialog_negative_btn);
            mNeBtn.setVisibility(View.GONE);
        }
    }

    /**
     * Show alertDialog
     *
     * @param activity activity instance
     * @param content show content
     * @param btnContent ok btn content
     * @param neContent negative btn content
     * @param onPoClickListener ok btn onClickListener
     * @param onNeClickListener negative btn onClickListener
     */
    public void showDialog(Activity activity, String content, String btnContent, String neContent,
                           final View.OnClickListener onPoClickListener,
                           final View.OnClickListener onNeClickListener) {
        if (activity == null) {
            return;
        }
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(activity, R.style.myCorDialog).create();
        }
        if (!mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
        mAlertDialog.setCanceledOnTouchOutside(false);
        Window window = mAlertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.common_alert_dialog);
            TextView contentTv = (TextView) window.findViewById(R.id.dialog_content);
            contentTv.setText(content);
            Button mOkBtn = (Button) window.findViewById(R.id.dialog_btn);
            mOkBtn.setText(btnContent);
            mOkBtn.setOnClickListener(onPoClickListener);
            View btnDivider = window.findViewById(R.id.dialog_btn_divider);
            btnDivider.setVisibility(View.VISIBLE);
            Button mNeBtn = (Button) window.findViewById(R.id.dialog_negative_btn);
            mNeBtn.setText(neContent);
            mNeBtn.setVisibility(View.VISIBLE);
            mNeBtn.setOnClickListener(onNeClickListener);
        }
    }
}
