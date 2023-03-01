package com.hh.androidbaselibrary.ui.objectBox.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.androidbaselibrary.R
import com.hh.androidbaselibrary.ui.objectBox.contract.ObjectBoxContract
import com.hh.androidbaselibrary.ui.objectBox.entity.User
import com.hh.androidbaselibrary.ui.objectBox.entity.User_
import com.hh.androidbaselibrary.ui.objectBox.presenter.ObjectBoxPresenter
import com.hh.androidbaselibrary.ui.objectBox.util.ObjectBoxUtil

/**
 * 此文件为系统自动生成的MVP基础代码,可自行编辑，依赖基础库参考 https://gitee.com/allen056/android_base_library
 * @author hHui
 * @Package com.hh.androidbaselibrary.ui.objectBox.view
 * @Description: 演示使用ObjectBox数据库框架
 * @date 2023/02/27 15:23:16
 */
class ObjectBoxActivity : BaseActivity(), ObjectBoxContract.View {
    private val presenter = ObjectBoxPresenter(this)

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_box)

        initView()
    }

    override fun initView() {
        presenter.create()
        val user = User(1, "a", "aaaaa")
//        val users =
//            ObjectBoxUtil.userBox.query(User_.name.equal("Tom").or(User_.hobby.equal(""))).build()
        ObjectBoxUtil.userBox.put(user)
    }

    /**
     *是否启用透明状态栏 true->启用 false->不启用
     */
    override fun transportStatusBar(): Boolean = false
}