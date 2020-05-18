package com.hh.baselibrary.http

import com.google.gson.Gson


/**
 *
 * Created by hHui on 2017/8/1.
 */

fun <T> T.toJsonString(): String {
    return Gson().toJson(this)
}

fun <T> String.toModel(type: Class<T>): T {
    return Gson().fromJson(this, type)
}

