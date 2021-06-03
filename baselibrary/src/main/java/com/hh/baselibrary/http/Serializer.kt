package com.hh.baselibrary.http

import com.alibaba.fastjson.JSON


/**
 *
 * Created by hHui on 2017/8/1.
 */

fun <T> T.toJsonString(): String {
    return JSON.toJSONString(this)
}

fun <T> T.toJsonStringWithDateFormat(dataFormat: String = "yyyy-MM-dd HH:mm:ss"): String {
    return JSON.toJSONStringWithDateFormat(this, dataFormat)
}

fun <T> String.toModel(type: Class<T>): T {
    return JSON.parseObject(this, type)
}

