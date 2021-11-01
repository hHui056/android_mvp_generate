package com.hh.baselibrary.log

import java.io.File
import java.util.concurrent.atomic.AtomicReference

/**
 *Create By hHui on 2018/4/4
 */
class Logger private constructor() {

    private var TAG = "pj_log: "  //控制台打印日志所有Log前添加TAG，方便赛选

    enum class Type {
        Logcat, File, Net
    }

    private lateinit var printer: ILog

    private var level: Int = VERBOSE

    constructor(type: Type, level: Int, file: File, host: String, port: Int) : this() {
        printer = when (type) {
            Type.Logcat -> CatLog()
            Type.File -> FileLog(file)
            Type.Net -> NetLog(host, port)
        }
        this.level = level
    }

    companion object {
        const val VERBOSE: Int = 0
        const val DEBUG: Int = 1
        const val INFO: Int = 2
        const val WARN: Int = 3
        const val ERROR: Int = 4
        private var logger: AtomicReference<Logger> = AtomicReference()
        private lateinit var logType: Type
        /**
         * 初始化
         */
        @JvmStatic
        fun init(type: Type, level: Int, file: File, host: String = "", port: Int = 0) {
            if (this.logger.get() == null) {
                logType = type
                if (type == Type.Net) {
                    if (host == "" || port == 0) {
                        throw Exception("使用远程日志必须传入服务器地址和端口")
                    }
                }
                val logger = Logger(type, level, file, host, port)
                this.logger = AtomicReference(logger)
            }
        }

        @JvmStatic
        fun dft(): Logger {
            if (logger.get() == null) {
                throw  Exception("Logger has not initialized, please invoke init method on application created.")
            } else {
                return logger.get()
            }
        }

        fun of(type: Type, level: Int, file: File, host: String = "", port: Int = 0): Logger {
            return Logger(type, level, file, host, port)
        }
    }

    @JvmOverloads
    fun v(tag: String, msg: String, t: Throwable? = null) {
        val showMsg = if (logType == Type.Logcat) "$TAG$msg" else msg
        if (level <= VERBOSE) printer.v(tag, showMsg, t)
    }

    @JvmOverloads
    fun d(tag: String, msg: String, t: Throwable? = null) {
        val showMsg = if (logType == Type.Logcat) "$TAG$msg" else msg
        if (level <= DEBUG) printer.d(tag, showMsg, t)
    }

    @JvmOverloads
    fun i(tag: String, msg: String, t: Throwable? = null) {
        val showMsg = if (logType == Type.Logcat) "$TAG$msg" else msg
        if (level <= INFO) printer.i(tag, showMsg, t)
    }

    @JvmOverloads
    fun w(tag: String, msg: String, t: Throwable? = null) {
        val showMsg = if (logType == Type.Logcat) "$TAG$msg" else msg
        if (level <= WARN) printer.w(tag, showMsg, t)
    }

    @JvmOverloads
    fun e(tag: String, msg: String, t: Throwable? = null) {
        val showMsg = if (logType == Type.Logcat) "$TAG$msg" else msg
        if (level <= ERROR) printer.e(tag, showMsg, t)
    }

    fun dispose() {
        printer.dispose()
    }

}