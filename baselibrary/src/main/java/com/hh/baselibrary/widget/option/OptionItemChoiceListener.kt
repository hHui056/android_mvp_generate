package com.hh.baselibrary.widget.option

/**
 * Created by hHui on 2020/5/15 0015.
 */
interface OptionItemChoiceListener<T> {
    fun onChoice(position: Int, data: T)
}