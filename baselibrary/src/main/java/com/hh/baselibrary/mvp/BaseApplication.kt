package com.hh.baselibrary.mvp

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import androidx.annotation.ColorInt
import com.hh.baselibrary.common.ContextManager

/**
 * Create By hHui on 2021/11/2
 *
 * Application基类
 */
abstract class BaseApplication : Application() {

    companion object {
        //主题颜色
        @SuppressLint("ResourceAsColor")
        @ColorInt
        var logoColor: Int = Color.parseColor("#377EB4")
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ContextManager.setApp(applicationContext)
        init()
    }

    /**
     * 初始APP主题颜色，如果不使用则使用默认颜色主题
     */
    fun initApplicationThemeColor(@ColorInt color: Int) {
        logoColor = color
    }

    /**
     * Application初始化工作放在此处
     */
    abstract fun init()
}