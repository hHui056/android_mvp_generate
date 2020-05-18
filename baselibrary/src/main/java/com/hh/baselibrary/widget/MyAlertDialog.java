package com.hh.baselibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyAlertDialog {
    private Context mContext;
    private SweetAlertDialog alertDialog;

    private LoadingDialog processDialog;

    public MyAlertDialog(Context activity) {
        mContext = activity;
    }

    public interface AlertClickBack {
        void onConfirm();

        void onCancel();
    }

    public interface CancleClickBask {
        void onCancel();
    }

    public void showDialogLoading(String message) {
        if (alertDialog != null && alertDialog.isShowing()) {
            closeDialogLoading();
        }
        if (processDialog == null) {
            processDialog = new LoadingDialog();
            processDialog.init(mContext);
        }
        processDialog.showLoading(message);
    }

    @SuppressLint("NewApi")
    public void closeDialogLoading() {
        if (mContext instanceof AppCompatActivity) {
            if (!((AppCompatActivity) mContext).isFinishing() && !((AppCompatActivity) mContext).isDestroyed()) {
                if (processDialog != null) {
                    processDialog.closeProgress();
                }
            }
        } else {
            if (processDialog != null) {
                processDialog.closeProgress();
            }
        }
    }

    public void dissmisDialog() {
        closeDialogLoading();
    }

    /**
     * 普通弹出框
     *
     * @param msg
     */
    public void alertMsg(String msg) {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.closeProgress();
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            closeDialogLoading();
        }
        alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE).setTitleText(msg).setContentText("");
        alertDialog.show();
    }

    /**
     * 成功弹出框
     *
     * @param msg
     */
    public void alertSuccessMsg(String msg) {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.closeProgress();
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            closeDialogLoading();
        }
        alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE).setTitleText(msg).setContentText("");
        alertDialog.show();
    }

    /**
     * 警告弹出框
     *
     * @param title
     * @param msg
     */
    public void alertWaringMsg(String title, String msg) {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.closeProgress();
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            closeDialogLoading();
        }
        alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE).setTitleText(title).setContentText(msg);
        alertDialog.show();
    }

    /**
     * 错误信息弹出框
     *
     * @param title
     * @param msg
     */
    public void alertErrorMsg(String title, String msg) {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.closeProgress();
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            closeDialogLoading();
        }
        alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE).setTitleText(title).setContentText(msg);
        alertDialog.show();
    }

    /**
     * 带确定取消按钮额弹出框
     *
     * @param title
     * @param msg
     * @param callBack
     */
    public void alertOption(String title, String msg, final AlertClickBack callBack) {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.closeProgress();
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            closeDialogLoading();
        }
        alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(title).setContentText(msg)
                .showCancelButton(true)
                .setCancelText("取消")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        alertDialog.dismissWithAnimation();
                        callBack.onConfirm();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        alertDialog.dismissWithAnimation();
                        callBack.onCancel();
                    }
                });
        alertDialog.show();
    }

}
