package com.hh.baselibrary.util.sm4

import com.hh.baselibrary.http.toJsonString
import com.hh.baselibrary.http.toModel

/**
 * Create By hHui on 2022/6/17 0017 下午 15:58
 */
object EncryptAndDecryptUtil {
    private val sm4Util = SM4Utils.build(SM4Constants.SECRET_KEY, SM4Constants.IV)

    /**
     * 请求数据加密
     */
    @JvmStatic
    fun <T> encryptData(request: T): String {
        return sm4Util.encryptData_CBC(request.toJsonString())
    }

    /**
     * 数据解密
     */
    @JvmStatic
    fun <T> decryptData(data: String?, a: Class<T>): T? {
        if (data == null || data == "") return null
        return try {
            val plainText = sm4Util.decryptData_CBC(data)
            plainText.toModel(a)
        } catch (e: Exception) {
            null
        }
    }

    @JvmStatic
    fun decryptData(data: String?): String? {
        if (data == null || data == "") return null
        return try {
            sm4Util.decryptData_CBC(data)
        } catch (e: Exception) {
            null
        }
    }
    
}