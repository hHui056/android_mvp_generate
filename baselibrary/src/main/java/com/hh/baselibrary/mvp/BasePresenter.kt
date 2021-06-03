package com.hh.baselibrary.mvp

/**
 * Created by hHui on 2021年3月23日
 */
abstract class BasePresenter {
    val tag = this.javaClass.simpleName
    abstract fun create()
    abstract fun start()
    abstract fun stop()
}