package com.hh.baselibrary.util.licence

import android.util.Log
import com.hh.baselibrary.common.ContextManager
import com.hh.baselibrary.http.HttpClient
import com.hh.baselibrary.util.ApkUtils
import com.hh.baselibrary.util.sm4.EncryptAndDecryptUtil
import io.reactivex.Observable

/**
 * Create By hHui on 2022/6/17 0017 下午 13:59
 */
class LicenceUtil {
    private val httpClient = HttpClient()
    private val baseUrl = "https://gitee.com/allen056/android_base_library/raw/master/licence/"

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
        return httpClient.getString(url).map {
            Log.d("pj_log", "check licence result: $it")
            val result = EncryptAndDecryptUtil.decryptData(it, Licence::class.java)
            result
        }
    }
}