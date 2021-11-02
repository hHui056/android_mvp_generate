package com.hh.androidbaselibrary

import com.hh.baselibrary.mvp.BaseApplication

/**
 * Create By hHui on 2021/11/2 0002 上午 11:37
 */
class MainApplication : BaseApplication() {
    override fun init() {
        setApplicationThemeColor(resources.getColor(R.color.error_stroke_color))
    }
}