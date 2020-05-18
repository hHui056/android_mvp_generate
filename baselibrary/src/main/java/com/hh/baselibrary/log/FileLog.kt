package com.hh.baselibrary.log

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hHui on 2018/4/4.
 *
 */
internal class FileLog(var file: File) : ILog {

    private val fileAppender: AsyncFileWriter = AsyncFileWriter(file)

    private fun format(level: String, tag: String, msg: String, t: Throwable?): String {
        return "${Date().stringFormat("yyyy/MM/dd HH:mm:ss.SSS")} - [TID:${Thread.currentThread().id}][$level][$tag]:$msg"
    }

    private fun print(level: String, tag: String, msg: String, t: Throwable?) {
        fileAppender.appendLine(format(level, tag, msg, t))
    }

    override fun d(tag: String, msg: String, t: Throwable?) {
        print("DEBUG", tag, msg, t)
    }

    override fun i(tag: String, msg: String, t: Throwable?) {
        print("INFO", tag, msg, t)
    }

    override fun w(tag: String, msg: String, t: Throwable?) {
        print("WARN", tag, msg, t)
    }

    override fun e(tag: String, msg: String, t: Throwable?) {
        print("ERROR", tag, msg, t)
    }

    override fun v(tag: String, msg: String, t: Throwable?) {
        print("VERBOSE", tag, msg, t)
    }

    override fun dispose() {
        fileAppender.dispose()
    }

    /**
     * 日期转化为字符串
     */
    fun Date.stringFormat(formatType: String): String {
        return SimpleDateFormat(formatType).format(this)
    }

}