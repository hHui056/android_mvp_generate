package com.hh.androidbaselibrary.ui.objectBox.util

import android.content.Context
import com.hh.androidbaselibrary.ui.objectBox.entity.MyObjectBox
import com.hh.androidbaselibrary.ui.objectBox.entity.User
import io.objectbox.Box
import io.objectbox.BoxStore

/**
 * Create By hHui on 2023/2/27 0027 下午 15:35
 */
object ObjectBoxUtil {
    private lateinit var store: BoxStore

    lateinit var userBox: Box<User>

    fun init(context: Context) {
        store = MyObjectBox.builder().androidContext(context.applicationContext).build()
        userBox = store.boxFor(User::class.java)
    }
}