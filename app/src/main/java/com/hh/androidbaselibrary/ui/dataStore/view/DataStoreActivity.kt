package com.hh.androidbaselibrary.ui.dataStore.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.androidbaselibrary.R
import com.hh.androidbaselibrary.ui.dataStore.contract.DataStoreContract
import com.hh.androidbaselibrary.ui.dataStore.presenter.DataStorePresenter
import com.hh.baselibrary.util.EasyDtaStore
import kotlinx.android.synthetic.main.activity_data_store.*

/**
 * 此文件为系统自动生成的MVP基础代码,可自行编辑，依赖基础库参考 https://gitee.com/allen056/android_base_library
 * @author hHui
 * @Package com.hh.androidbaselibrary.ui.dataStore.view
 * @Description: 使用DataStore存储键值对
 * @date 2023/02/21 14:05:47
 */
class DataStoreActivity : BaseActivity(), DataStoreContract.View {
    private val presenter = DataStorePresenter(this)

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

        initView()
    }

    override fun initView() {
        presenter.create()

        save.setOnClickListener { EasyDtaStore.putData("name", "张三" + Math.random()) }

        read_key.setOnClickListener {
            result.text = EasyDtaStore.getData("name", "这是默认值")

        }
    }
    /**
     *是否启用透明状态栏 true->启用 false->不启用
     */
    override fun transportStatusBar(): Boolean = false
}