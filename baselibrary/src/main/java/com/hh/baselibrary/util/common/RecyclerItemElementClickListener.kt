package com.hh.baselibrary.util.common

/**
 * Create By hHui on 2022/5/9 0009 下午 17:25
 */
interface RecyclerItemElementClickListener<T> {
    /**
     * @param id 点击元素的Id
     */
    fun onClick(id: Int, position: Int, data: T)
}