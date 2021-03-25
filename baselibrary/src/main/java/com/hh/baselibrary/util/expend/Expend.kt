package com.hh.baselibrary.util.expend

import android.annotation.SuppressLint
import android.widget.EditText
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Create By hHui on 2021/3/25 0025 下午 14:35
 *
 * 已知类型的扩展
 */

//时间转字符串显示 默认转换到秒
@SuppressLint("SimpleDateFormat")
fun Date.toShow(type: DateType = DateType.SECOND): String {
    return when (type) {
        DateType.YEAR -> SimpleDateFormat("yyyy").format(this)
        DateType.MONTH -> SimpleDateFormat("yyyy-MM").format(this)
        DateType.DAY -> SimpleDateFormat("yyyy-MM-dd").format(this)
        DateType.HOUR -> SimpleDateFormat("yyyy-MM-dd HH").format(this)
        DateType.MINUTE -> SimpleDateFormat("yyyy-MM-dd HH:mm").format(this)
        DateType.SECOND -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
    }
}

//字符串转时间
@Throws
@SuppressLint("SimpleDateFormat")
fun String.toDate(type: DateType = DateType.SECOND): Date {
    return when (type) {
        DateType.YEAR -> SimpleDateFormat("yyyy").parse(this)
        DateType.MONTH -> SimpleDateFormat("yyyy-MM").parse(this)
        DateType.DAY -> SimpleDateFormat("yyyy-MM-dd").parse(this)
        DateType.HOUR -> SimpleDateFormat("yyyy-MM-dd HH").parse(this)
        DateType.MINUTE -> SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this)
        DateType.SECOND -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)
    }
}

//获取输入框字符串 未输入返回null
fun EditText.getString(): String? {
    val input = this.text.toString().trim()
    return if (input == "") null else input
}

//获取输入框Double 未输入或者类型错误返回null
fun EditText.getDouble(): Double? {
    return try {
        val input = this.text.toString().trim()
        input.toDouble()
    } catch (e: Exception) {
        null
    }
}

//获取输入框Int 未输入或者类型错误返回null
fun EditText.getInt(): Int? {
    return try {
        val input = this.text.toString().trim()
        input.toInt()
    } catch (e: Exception) {
        null
    }
}

//获取输入框Long 未输入或者类型错误返回null
fun EditText.getLong(): Long? {
    return try {
        val input = this.text.toString().trim()
        input.toLong()
    } catch (e: Exception) {
        null
    }
}

//清除输入框输入
fun EditText.clear() {
    this.setText("")
}

//设置Edittext输入框值
fun EditText.text(content: String) {
    this.setText(content)
    this.setSelection(content.length)
}

/** 删除字符串的最后一个字符 **/
fun String.deleteLastChar(): String {
    return this.substring(0, this.length - 1)
}