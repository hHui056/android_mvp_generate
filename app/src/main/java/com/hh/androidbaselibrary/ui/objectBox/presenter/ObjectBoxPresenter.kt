package com.hh.androidbaselibrary.ui.objectBox.presenter

import com.hh.androidbaselibrary.ui.objectBox.contract.ObjectBoxContract
import com.hh.androidbaselibrary.ui.objectBox.model.ObjectBoxModel
/**
 * 此文件为系统自动生成的MVP基础代码,可自行编辑，依赖基础库参考 https://gitee.com/allen056/android_base_library
 * @author hHui
 * @Package com.hh.androidbaselibrary.ui.objectBox.presenter
 * @Description: 演示使用ObjectBox数据库框架
 * @date 2023/02/27 15:23:16
 */
class ObjectBoxPresenter(private val view:ObjectBoxContract.View):ObjectBoxContract.Presenter {
    private val tag = this.javaClass.simpleName

    private val model = ObjectBoxModel()

    override fun create() {
    }

    override fun start() {

    }

    override fun stop() {
    }
} 