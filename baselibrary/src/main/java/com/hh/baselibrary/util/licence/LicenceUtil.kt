package com.hh.baselibrary.util.licence

import android.util.Log
import com.hh.baselibrary.common.ContextManager
import com.hh.baselibrary.http.HttpClient
import com.hh.baselibrary.util.ApkUtils
import io.reactivex.Observable

/**
 * Create By hHui on 2022/6/17 0017 下午 13:59
 */
class LicenceUtil {
    private val httpClient = HttpClient()
    private val baseUrl = "https://gitee.com/allen056/android_base_library/raw/licence/licence/"

    companion object {
        val instance: LicenceUtil by lazy {
            LicenceUtil()
        }
    }

    /**
     * 获取授权文件
     */
    fun getLicence(): Observable<Licence> {
        val url = "$baseUrl${ApkUtils.getPackageName(ContextManager.getApp())}.json"
        Log.d("pj_log", "licence url: $url")
        return httpClient.getJson(url, Licence::class.java)
    }

}