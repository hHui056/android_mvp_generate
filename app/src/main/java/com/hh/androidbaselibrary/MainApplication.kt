package com.hh.androidbaselibrary

import android.annotation.SuppressLint
import com.hh.androidbaselibrary.ui.objectBox.util.ObjectBoxUtil
import com.hh.baselibrary.mvp.BaseApplication
import com.hh.baselibrary.widget.ios.DialogStyle

/**
 * Create By hHui on 2021/11/2 0002 上午 11:37
 */
class MainApplication : BaseApplication() {

    @SuppressLint("ResourceType")
    override fun init() {
        setApplicationThemeColor(resources.getColor(R.color.action_sheet_blue), DialogStyle.IOS)
        ObjectBoxUtil.init(this)
    }

}