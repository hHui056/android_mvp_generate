package com.hh.baselibrary.mvp

import com.hh.baselibrary.log.Logger
import com.hh.baselibrary.util.common.LocalThrowable
import com.hh.baselibrary.util.common.ServerThrowable

/**
 * Create By hHui on 2021/3/23 0023
 */
open class BaseModel {
    val tag = this.javaClass.simpleName

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