package com.hh.baselibrary.util

import com.tencent.mmkv.MMKV

/**
 * Create By hHui on 2022/12/2 0002 上午 10:32
 */
object MMKVUtil {
    private val kv = MMKV.defaultMMKV()

    @JvmStatic
    fun putInt(key: String, value: Int): Boolean {
        return kv.encode(key, value)
    }

    @JvmStatic
    fun getInt(key: String, defaultValue: Int = -1): Int {
        return kv.decodeInt(key, defaultValue)
    }

    @JvmStatic
    fun putString(key: String, value: String): Boolean {
        return kv.encode(key, value)
    }

    @JvmStatic
    fun getString(key: String, defaultValue: String = ""): String {
        return if (kv.decodeString(key, defaultValue) == null) defaultValue else kv.decodeString(key, defaultValue)!!
    }

    @JvmStatic
    fun putLong(key: String, value: Long): Boolean {
        return kv.encode(key, value)
    }

    @JvmStatic
    fun getLong(key: String, defaultValue: Long = -1): Long {
        return kv.decodeLong(key, defaultValue)
    }

    @JvmStatic
    fun putDouble(key: String, value: Double): Boolean {
        return kv.encode(key, value)
    }

    @JvmStatic
    fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        return kv.decodeDouble(key, defaultValue)
    }


    @JvmStatic
    fun putBool(key: String, value: Boolean): Boolean {
        return kv.encode(key, value)
    }

    @JvmStatic
    fun getBool(key: String, defaultValue: Boolean = false): Boolean {
        return kv.decodeBool(key, defaultValue)
    }

    @JvmStatic
    fun putFloat(key: String, value: Float): Boolean {
        return kv.encode(key, value)
    }

    @JvmStatic
    fun getFloat(key: String, defaultValue: Float = -1F): Float {
        return kv.decodeFloat(key, defaultValue)
    }

    @JvmStatic
    fun clearAll() {
        kv.clearAll()
    }

    @JvmStatic
    fun remove(key: String) {
        kv.removeValueForKey(key)
    }
}