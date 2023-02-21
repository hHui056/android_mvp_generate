package com.hh.androidbaselibrary.ui.dataStore.presenter

import com.hh.androidbaselibrary.ui.dataStore.contract.DataStoreContract
import com.hh.androidbaselibrary.ui.dataStore.model.DataStoreModel
/**
 * 此文件为系统自动生成的MVP基础代码,可自行编辑，依赖基础库参考 https://gitee.com/allen056/android_base_library
 * @author hHui
 * @Package com.hh.androidbaselibrary.ui.dataStore.presenter
 * @Description: 使用DataStore存储键值对
 * @date 2023/02/21 14:05:47
 */
class DataStorePresenter(private val view:DataStoreContract.View):DataStoreContract.Presenter {
    private val tag = this.javaClass.simpleName

    private val model = DataStoreModel()

    override fun create() {
    }

    override fun start() {

    }

    override fun stop() {
    }
} 