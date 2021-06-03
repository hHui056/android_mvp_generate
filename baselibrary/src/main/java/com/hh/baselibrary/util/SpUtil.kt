package com.hh.baselibrary.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.collection.SimpleArrayMap
import java.lang.ref.WeakReference

/**
 * Created by hHui on 2019/11/29 0029.
 */
@SuppressLint("ApplySharedPref")
class SpUtil(spName: String, mcontext: WeakReference<Context>) {

    private val sp: SharedPreferences =
        mcontext.get()!!.getSharedPreferences(spName, Context.MODE_PRIVATE)

    /**
     * SP中写入String

     * @param key      键
     * *
     * @param value    值
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun put(key: String, value: String, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().putString(key, value).commit()
        } else {
            sp.edit().putString(key, value).apply()
        }
    }

    /**
     * SP中读取String

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getString(key: String, defaultValue: String = ""): String {
        return if (sp.getString(key, defaultValue) == null) defaultValue else sp.getString(key,defaultValue)!!
    }

    /**
     * SP中写入int

     * @param key      键
     * *
     * @param value    值
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun put(key: String, value: Int, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit()
        } else {
            sp.edit().putInt(key, value).apply()
        }
    }

    /**
     * SP中读取int

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getInt(key: String, defaultValue: Int = -1): Int {
        return sp.getInt(key, defaultValue)
    }

    /**
     * SP中写入long

     * @param key      键
     * *
     * @param value    值
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun put(key: String, value: Long, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit()
        } else {
            sp.edit().putLong(key, value).apply()
        }
    }

    /**
     * SP中读取long

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getLong(key: String, defaultValue: Long = -1L): Long {
        return sp.getLong(key, defaultValue)
    }

    /**
     * SP中写入float

     * @param key      键
     * *
     * @param value    值
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun put(key: String, value: Float, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit()
        } else {
            sp.edit().putFloat(key, value).apply()
        }
    }

    /**
     * SP中读取float

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getFloat(key: String, defaultValue: Float = -1f): Float {
        return sp.getFloat(key, defaultValue)
    }

    /**
     * SP中写入boolean

     * @param key      键
     * *
     * @param value    值
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun put(key: String, value: Boolean, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit()
        } else {
            sp.edit().putBoolean(key, value).apply()
        }
    }

    /**
     * SP中读取boolean

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    /**
     * SP中写入String集合

     * @param key      键
     * *
     * @param values   值
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun put(key: String, values: Set<String>, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().putStringSet(key, values).commit()
        } else {
            sp.edit().putStringSet(key, values).apply()
        }
    }

    /**
     * SP中读取StringSet

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getStringSet(
        key: String,
        defaultValue: Set<String> = emptySet<String>()
    ): MutableSet<String>? {
        return sp.getStringSet(key, defaultValue)
    }

    /**
     * SP中获取所有键值对

     * @return Map对象
     */
    val all: Map<String, *>
        get() = sp.all

    /**
     * SP中是否存在该key

     * @param key 键
     * *
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    operator fun contains(key: String): Boolean {
        return sp.contains(key)
    }

    /**
     * SP 中移除该key

     * @param key      键
     * *
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun remove(key: String, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().remove(key).commit()
        } else {
            sp.edit().remove(key).apply()
        }
    }

    /**
     * SP中清除所有数据

     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * *                 `false`: [SharedPreferences.Editor.apply]
     */
    @JvmOverloads
    fun clear(isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().clear().commit()
        } else {
            sp.edit().clear().apply()
        }
    }

    companion object {
        lateinit var instance: SpUtil

        private val SP_UTILS_MAP = SimpleArrayMap<String, SpUtil>()

        /**
         * 获取SP实例

         * @param spName sp名
         * *
         * @return [SpUtil]
         */
        private fun create(spName: String, context: WeakReference<Context>): SpUtil {
            var spName = spName
            if (isSpace(spName)) spName = "spUtils"
            var spUtilUtils: SpUtil? = SP_UTILS_MAP.get(spName)
            if (spUtilUtils == null) {
                spUtilUtils = SpUtil(spName, context)
                SP_UTILS_MAP.put(spName, spUtilUtils)
            }
            return spUtilUtils
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }

        fun init(spName: String, context: WeakReference<Context>) {
            instance = create(spName, context)
        }
    }
}