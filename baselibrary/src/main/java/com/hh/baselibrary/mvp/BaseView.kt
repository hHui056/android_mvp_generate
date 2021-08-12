package com.hh.baselibrary.mvp

import com.hh.baselibrary.widget.MyAlertDialog

/**
 * Create By hHui on 2021年3月23日
 */
interface BaseView {
    fun showProgressCanCle(msg: String = "Loading")
    fun showProgress(msg: String = "Loading...")
    fun closeProgress()
    fun alertSuccess(msg: String)
    fun alertError(title: String = "", msg: String)
    fun alertOneButtonOption(title: String, msg: String, callback: MyAlertDialog.ClickBack, buttonText: String = "确定")
    fun alertOption(title: String, msg: String, callback: MyAlertDialog.AlertClickBack, cancel: String = "取消", sure: String = "确定")
    fun showToast(msg: String)
    fun miss()
}