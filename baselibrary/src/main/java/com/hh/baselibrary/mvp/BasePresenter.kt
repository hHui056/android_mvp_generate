package com.hh.baselibrary.mvp

import com.hh.baselibrary.log.Logger
import com.hh.baselibrary.util.common.LocalThrowable
import com.hh.baselibrary.util.common.ServerThrowable

/**
 * Created by hHui on 2021年3月23日
 */
abstract class BasePresenter {
    val tag = this.javaClass.simpleName
    abstract fun create()
    abstract fun start()
    abstract fun stop()

    fun logError(msg: String, it: Throwable) {
        val sb = StringBuffer(msg)
        if (it is ServerThrowable) {
            sb.append(" 服务器异常")
        } else if (it is LocalThrowable) {
            sb.append(" 本地异常")
        }
        Logger.dft().e(tag, "$sb 【${it.message}】")
    }
}