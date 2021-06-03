package com.hh.baselibrary.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hh.baselibrary.R
import com.hh.baselibrary.util.StatusBarUtil
import com.hh.baselibrary.util.ToastUtil
import com.hh.baselibrary.widget.MyAlertDialog
import com.hh.baselibrary.widget.ios.DialogStyle

/**
 * Create By hHui on 2021/3/23 0023
 *
 * Activity基类，所有Activity都继承此类
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    val tag = this.javaClass.simpleName

    lateinit var alertDialog: MyAlertDialog   //提示框

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alertDialog = MyAlertDialog(this, DialogStyle.IOS)

        setStatusBar() //设置沉浸式状态栏
    }

    abstract fun initView()

    override fun showProgress(msg: String) {
        alertDialog.showDialogLoading(msg)
    }

    override fun showProgressCanCle(msg: String) {
        alertDialog.showDialogLoading(msg)
    }

    override fun closeProgress() {
        alertDialog.closeDialogLoading()
    }

    override fun alertError(title: String, msg: String) {
        alertDialog.alertErrorMsg(title, msg)
    }

    override fun alertSuccess(msg: String) {
        alertDialog.alertSuccessMsg(msg)
    }

    override fun alertOption(
        title: String, msg: String, callback: MyAlertDialog.AlertClickBack, cancel: String,
        sure: String
    ) = alertDialog.alertOption(title, msg, callback, cancel, sure)

    override fun showToast(msg: String) {
        ToastUtil.showToast(this, msg)
    }

    override fun miss() = finish()


    fun <T> jump2Activity(cla: Class<T>, isFinishBefore: Boolean) {
        startActivity(Intent(this, cla))
        if (isFinishBefore) finish()
    }

    fun <T> jump2Activity(cla: Class<T>, map: Map<String, String>, isFinishBefore: Boolean) {
        val intent = Intent(this, cla)
        map.forEach { intent.putExtra(it.key, it.value) }
        startActivity(intent)
        if (isFinishBefore) finish()
    }

    fun setStatusBar() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setStatusBarColor(this, resources.getColor(R.color.logoColor))
    }
}