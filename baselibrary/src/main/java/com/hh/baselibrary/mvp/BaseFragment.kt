package com.hh.baselibrary.mvp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hh.baselibrary.widget.MyAlertDialog

/**
 * Create By hHui on 2021/5/31 0031 下午 13:29
 */
abstract class BaseFragment : Fragment(), BaseView {

    var viewCreatedListener: ViewCreatedListener? = null

    lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
    }

    abstract fun initView()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view!!.setOnTouchListener { _, motionEvent ->
            baseActivity.onTouchEvent(motionEvent)
            false
        }
    }

    override fun showProgressCanCle(msg: String) {
        baseActivity.showProgressCanCle(msg)
    }


    override fun showProgress(msg: String) {
        baseActivity.showProgress(msg)
    }

    override fun closeProgress() {
        baseActivity.closeProgress()
    }

    override fun alertError(title: String, msg: String) {
        baseActivity.alertError(title, msg)
    }

    override fun showToast(msg: String) {
        baseActivity.showToast(msg)
    }

    override fun alertOption(
        title: String,
        msg: String,
        callback: MyAlertDialog.AlertClickBack,
        cancel: String,
        sure: String
    ) {
        baseActivity.alertOption(title, msg, callback, cancel, sure)
    }

    override fun alertOneButtonOption(title: String, msg: String, callback: MyAlertDialog.ClickBack, buttonText: String) {
        baseActivity.alertOneButtonOption(title, msg, callback, buttonText)
    }

    override fun alertSuccess(msg: String) {
        baseActivity.alertSuccess(msg)
    }

    override fun miss() {
        baseActivity.finish()
    }

    fun hintKeyBoard() {
        baseActivity.hintKeyBoard()
    }

    interface ViewCreatedListener {
        fun onViewCreated()
    }
}