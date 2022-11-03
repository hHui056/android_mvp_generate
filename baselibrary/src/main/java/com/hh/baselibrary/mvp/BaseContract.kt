package com.hh.baselibrary.mvp

import com.hh.baselibrary.widget.MyAlertDialog

/**
 * Create By hHui on 2022/4/28 0028 上午 9:15
 */
interface BaseContract {

    interface Presenter {
        fun create()
        fun start()
        fun stop()
    }

    interface View {
        fun showProgressCanCle(msg: String = "Loading", cancelText: String = "取消", callback: MyAlertDialog.CancelClickBack? = null)
        fun showProgress(msg: String = "Loading...")
        fun closeProgress()
        fun alertSuccess(msg: String)
        fun alertError(title: String = "", msg: String)
        fun alertOneButtonOption(title: String, msg: String, callback: MyAlertDialog.ClickBack, buttonText: String = "确定")
        fun alertOption(title: String, msg: String, callback: MyAlertDialog.AlertClickBack, cancel: String = "取消", sure: String = "确定")
        fun showToast(msg: String)
        fun miss()
    }

    interface Model {

    }
}