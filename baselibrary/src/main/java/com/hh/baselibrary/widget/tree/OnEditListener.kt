package com.hh.baselibrary.widget.tree

/**
 * Create By hHui on 2022/5/24 0024 下午 16:33
 */
interface OnEditListener {
    /** 在此项下新增 **/
    fun onAdd(node: Node)

    /** 删除 **/
    fun onDelete(node: Node)
}