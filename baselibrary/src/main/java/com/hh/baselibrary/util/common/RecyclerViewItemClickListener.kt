package com.hh.baselibrary.util.common

/**
 * Create By hHui on 2021/5/18 0018 下午 14:05
 */
interface RecyclerViewItemClickListener<T> {
    fun onClick(position: Int, data: T)
}