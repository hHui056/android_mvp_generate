package com.hh.baselibrary.mvp

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import androidx.annotation.ColorRes
import com.hh.baselibrary.common.ContextManager
import com.hh.baselibrary.widget.ios.DialogStyle
import com.tencent.mmkv.MMKV

/**
 * Create By hHui on 2021/11/2
 *
 * Application基类
 */
abstract class BaseApplication : Application() {
    private val activities = ArrayList<BaseActivity>()

    companion object {
        lateinit var instance: BaseApplication
        //主题颜色
        @SuppressLint("ResourceType")
        @ColorRes
        var logoColor: Int = Color.parseColor("#377EB4")
            private set

        //弹窗风格
        var dialogStyle: DialogStyle = DialogStyle.SweetDialog
            private set
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        ContextManager.setApp(applicationContext)
        MMKV.initialize(this)
        init()
    }

    /**
     * 设置APP主题颜色，弹窗风格，如果不使用则使用默认颜色主题
     */
    fun setApplicationThemeColor(
        @SuppressLint("ResourceType") @ColorRes color: Int = Color.parseColor("#377EB4"),
        style: DialogStyle = DialogStyle.SweetDialog
    ) {
        logoColor = color
        dialogStyle = style
    }

    fun addActivity(activity: BaseActivity) {
        activities.add(activity)
    }

    fun removeActivity(activity: BaseActivity): Boolean = activities.remove(activity)

    fun closeAllActivities() {
        activities.forEach { it.finish() }
        activities.clear()
    }

    /**
     * Application初始化工作放在此处
     */
    abstract fun init()
}