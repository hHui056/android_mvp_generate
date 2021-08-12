package com.hh.baselibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hh.baselibrary.widget.ios.DialogStyle;
import com.hh.baselibrary.widget.ios.IosAlertDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyAlertDialog {
    private Context mContext;
    private SweetAlertDialog sweetAlertDialog;
    private IosAlertDialog iosAlertDialog;
    private LoadingDialog processDialog;
    //dialog样式
    private DialogStyle dialogStyle = DialogStyle.SweetDialog;

    public MyAlertDialog(Context activity) {
        mContext = activity;
    }

    public MyAlertDialog(Context activity, DialogStyle dialogStyle) {
        mContext = activity;
        this.dialogStyle = dialogStyle;
    }

    public DialogStyle getDialogStyle() {
        return dialogStyle;
    }

    public void setDialogStyle(DialogStyle dialogStyle) {
        this.dialogStyle = dialogStyle;
    }

    public void showDialogLoading(String message) {
        closeAllDialog();
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

    private void closeAllDialog() {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.closeProgress();
        }
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
        }
        if (iosAlertDialog != null && iosAlertDialog.isShowing()) {
            iosAlertDialog.dismiss();
        }
    }

    public void dismissDialog() {
        closeAllDialog();
    }

    /**
     * 普通弹出框
     *
     * @param msg
     */
    public void alertMsg(String msg) {
        closeAllDialog();
        if (dialogStyle == DialogStyle.IOS) {
            iosAlertDialog = new IosAlertDialog(mContext).builder().setMsg(msg);
            iosAlertDialog.show();
        } else if (dialogStyle == DialogStyle.SweetDialog) {
            if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
                closeDialogLoading();
            }
            sweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE).setTitleText(msg).setContentText("");
            sweetAlertDialog.show();
        }
    }

    /**
     * 成功弹出框
     *
     * @param msg
     */
    public void alertSuccessMsg(String msg) {
        closeAllDialog();
        if (dialogStyle == DialogStyle.IOS) {
            iosAlertDialog = new IosAlertDialog(mContext).builder().setMsg(msg);
            iosAlertDialog.show();
        } else {
            sweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE).setTitleText(msg).setContentText("");
            sweetAlertDialog.show();
        }
    }

    /**
     * 警告弹出框
     *
     * @param title
     * @param msg
     */
    public void alertWaringMsg(String title, String msg) {
        closeAllDialog();
        if (dialogStyle == DialogStyle.IOS) {
            iosAlertDialog = new IosAlertDialog(mContext).builder().setMsg(msg);
            iosAlertDialog.show();
        } else {
            sweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE).setTitleText(title).setContentText(msg);
            sweetAlertDialog.show();
        }
    }

    /**
     * 错误信息弹出框
     *
     * @param title
     * @param msg
     */
    public void alertErrorMsg(String title, String msg) {
        closeAllDialog();
        if (dialogStyle == DialogStyle.IOS) {
            iosAlertDialog = new IosAlertDialog(mContext).builder().setTitle(title).setMsg(msg);
            iosAlertDialog.show();
        } else {
            sweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE).setTitleText(title).setContentText(msg);
            sweetAlertDialog.show();
        }
    }

    /**
     * 显示只有一个选项的弹出框
     */
    public void alertOneButtonOption(String title, String msg, final ClickBack callback, String buttonText) {
        closeAllDialog();
        if (dialogStyle == DialogStyle.IOS) {
            iosAlertDialog = new IosAlertDialog(mContext).builder().setMsg(msg).setTitle(title).setPositiveButton(buttonText, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iosAlertDialog.dismiss();
                    callback.onClick();
                }
            });
            iosAlertDialog.show();
        } else {
            sweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText(title).setContentText(msg)
                    .showCancelButton(false)
                    .setConfirmText(buttonText)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            MyAlertDialog.this.sweetAlertDialog.dismissWithAnimation();
                            callback.onClick();
                        }
                    });
            sweetAlertDialog.show();
        }
    }


    /**
     * 带确定取消按钮额弹出框
     *
     * @param title
     * @param msg
     * @param callBack
     */
    public void alertOption(String title, String msg, final AlertClickBack callBack, String cancel, String sure) {
        closeAllDialog();
        if (dialogStyle == DialogStyle.IOS) {
            iosAlertDialog = new IosAlertDialog(mContext).builder().setMsg(msg).setTitle(title).setPositiveButton(sure, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iosAlertDialog.dismiss();
                    callBack.onConfirm();
                }
            }).setNegativeButton(cancel, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iosAlertDialog.dismiss();
                    callBack.onCancel();
                }
            });
            iosAlertDialog.show();
        } else {
            sweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText(title).setContentText(msg)
                    .showCancelButton(true)
                    .setCancelText(cancel)
                    .setConfirmText(sure)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            MyAlertDialog.this.sweetAlertDialog.dismissWithAnimation();
                            callBack.onConfirm();
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            MyAlertDialog.this.sweetAlertDialog.dismissWithAnimation();
                            callBack.onCancel();
                        }
                    });
            sweetAlertDialog.show();
        }

    }

    public interface AlertClickBack {
        void onConfirm();

        void onCancel();
    }

    public interface ClickBack {
        void onClick();
    }

    public interface CancelClickBask {
        void onCancel();
    }
}
