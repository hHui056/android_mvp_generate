package com.hh.baselibrary.common

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by hHui on 2019/11/29 0029.
 */
@SuppressLint("StaticFieldLeak")
object ContextManager {
    private lateinit var app: Context

    fun setApp(app: Context) {
        ContextManager.app = app
    }

    fun getApp(): Context {
        return app
    }
}