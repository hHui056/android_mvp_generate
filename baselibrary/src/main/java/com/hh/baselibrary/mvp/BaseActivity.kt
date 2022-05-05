package com.hh.baselibrary.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.hh.baselibrary.util.StatusBarUtil
import com.hh.baselibrary.util.ToastUtil
import com.hh.baselibrary.widget.MyAlertDialog

/**
 * Create By hHui on 2021/3/23 0023
 *
 * Activity基类，所有Activity都继承此类
 */
abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    val tag = this.javaClass.simpleName

    lateinit var alertDialog: MyAlertDialog   //提示框

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alertDialog = MyAlertDialog(this, BaseApplication.dialogStyle)

        setStatusBar() //设置沉浸式状态栏
    }

    abstract fun initView()

    abstract fun transportStatusBar(): Boolean

    override fun showProgress(msg: String) {
        alertDialog.showDialogLoading(msg)
    }

    override fun showProgressCanCle(
        msg: String, cancelText: String, callback: MyAlertDialog.CancelClickBack?
    ) {
        alertDialog.showDialogLoadingCancel(msg, cancelText, callback)
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
        title: String,
        msg: String,
        callback: MyAlertDialog.AlertClickBack,
        cancel: String,
        sure: String
    ) {
        alertDialog.alertOption(title, msg, callback, cancel, sure)
    }

    override fun alertOneButtonOption(
        title: String, msg: String, callback: MyAlertDialog.ClickBack, buttonText: String
    ) {
        alertDialog.alertOneButtonOption(title, msg, callback, buttonText)
    }

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

    /**
     * 设置沉浸式状态栏
     */
    private fun setStatusBar() {
        if (transportStatusBar()) {
            StatusBarUtil.setTranslucentStatus(this)
        } else {
            StatusBarUtil.setRootViewFitsSystemWindows(this, true)
            StatusBarUtil.setStatusBarColor(this, BaseApplication.logoColor)
        }
    }

    /**
     * 关闭软键盘
     */
    fun hintKeyBoard() {
        //拿到InputMethodManager
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //如果window上view获取焦点 && view不为空
        if (imm.isActive && currentFocus != null) {
            //拿到view的token 不为空
            if (currentFocus?.windowToken != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(
                    currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus != null) {
                hintKeyBoard()
            }
        }
        return super.onTouchEvent(event)
    }
}