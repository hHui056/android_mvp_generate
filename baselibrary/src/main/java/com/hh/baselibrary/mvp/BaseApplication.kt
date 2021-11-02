package com.hh.baselibrary.mvp

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.hh.baselibrary.common.ContextManager
import com.hh.baselibrary.widget.ios.DialogStyle

/**
 * Create By hHui on 2021/11/2
 *
 * Application基类
 */
abstract class BaseApplication : Application() {

    companion object {
        //主题颜色
        @SuppressLint("ResourceAsColor")
        @ColorRes
        var logoColor: Int = Color.parseColor("#377EB4")
            private set

        //弹窗风格
        var dialogStyle: DialogStyle = DialogStyle.SweetDialog
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ContextManager.setApp(applicationContext)
        init()
    }

    /**
     * 设置APP主题颜色，弹窗风格，如果不使用则使用默认颜色主题
     */
    fun setApplicationThemeColor(
        @ColorRes color: Int = Color.parseColor("#377EB4"),
        style: DialogStyle = DialogStyle.SweetDialog
    ) {
        logoColor = color
        dialogStyle = style
    }


    /**
     * Application初始化工作放在此处
     */
    abstract fun init()
}