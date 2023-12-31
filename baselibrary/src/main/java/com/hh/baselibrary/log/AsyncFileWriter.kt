package com.hh.baselibrary.log

import android.annotation.SuppressLint
import com.hh.baselibrary.async.RxBus
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * 如果文件不存在，新建文件，如果文件存在，追加。
 *
 * Created by hHui on 2018/4/4.
 */
@SuppressLint("CheckResult")
class AsyncFileWriter(private val file: File) {
    private var rxBus: RxBus

    private val nowDay: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val date = Date()
            val sdf = SimpleDateFormat("yyyy_MM_dd")
            return sdf.format(date)
        }

    init {
        if (!file.exists()) {
            val fileParent = File(file.parent)
            if (!fileParent.exists()) {//如果目录结构不存在先创建目录结构
                fileParent.mkdirs()
            }
            file.createNewFile()
        }

        rxBus = RxBus.newInstance
        rxBus.register(this, Data::class.java).observeOn(Schedulers.newThread()).subscribe({
            val logFile = File(file.absolutePath, "$nowDay.log")
            if (!logFile.exists()) logFile.createNewFile()
            when (it.type) {
                Type.Append -> logFile.appendText(it.content)
                Type.AppendLine -> logFile.appendText("${it.content}\n")
            }
        }, {
            Logger.dft().e(LogTag.Utils, it.toString())
        })
    }

    fun append(content: String) {
        rxBus.post(Data(content, Type.Append))
    }

    fun appendLine(content: String) {
        rxBus.post(Data(content, Type.AppendLine))
    }

    fun dispose() {
        rxBus.unregister(this, Data::class.java)
    }

    private data class Data(val content: String, val type: Type) : RxBus.EventType()

    private enum class Type {
        Append, AppendLine
    }
}