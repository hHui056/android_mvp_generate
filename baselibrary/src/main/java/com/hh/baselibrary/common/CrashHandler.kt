package com.hh.baselibrary.common

import android.annotation.SuppressLint
import android.content.Context
import com.hh.baselibrary.log.LogTag
import com.hh.baselibrary.log.Logger

/**
 *Create By hHui on 2018/4/4
 */
@SuppressLint("StaticFieldLeak")
object CrashHandler : Thread.UncaughtExceptionHandler {

    lateinit var appContext: Context

    fun init(appContext: Context) {
        this.appContext = appContext
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        val info = StringBuffer()
        info.append("【message】:" + ex.message).append("\n")
        info.append("【cause】:" + ex.cause).append("\n")
        ex.stackTrace.forEach {
            info.append("【file】:" + it.fileName)
            info.append("【class】:" + it.className)
            info.append("【method】:" + it.methodName)
            info.append("【line】:" + it.lineNumber)
            info.append("\n")
        }
        Logger.dft().e(LogTag.GlobalThrowable, info.toString())
        System.exit(0)

    }

}