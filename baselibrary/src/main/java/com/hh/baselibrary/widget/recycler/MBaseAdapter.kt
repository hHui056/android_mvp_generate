package com.hh.baselibrary.widget.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hh.baselibrary.util.common.RecyclerItemElementClickListener
import com.hh.baselibrary.util.common.RecyclerViewItemClickListener

/**
 * Create By hHui on 2022/5/9 0009 下午 15:16
 */
abstract class MBaseAdapter<T>(var list: MutableList<T>) : RecyclerView.Adapter<MBaseAdapter<T>.MBaseViewHolder>() {
    var recyclerViewItemClickListener: RecyclerViewItemClickListener<T>? = null

    var recyclerItemElementClickListener: RecyclerItemElementClickListener<T>? = null

    var listenerIds: Array<Int>? = null

    open inner class MBaseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                recyclerViewItemClickListener?.onClick(adapterPosition, list[adapterPosition])
            }
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    val child = view.getChildAt(i)
                    child.setOnClickListener {
                        if (recyclerItemElementClickListener == null || listenerIds == null) {
                            recyclerViewItemClickListener?.onClick(adapterPosition, list[adapterPosition])
                        } else {
                            if (listenerIds!!.contains(child.id)) {
                                recyclerItemElementClickListener?.onClick(child.id, adapterPosition, list[adapterPosition])
                            } else {
                                recyclerViewItemClickListener?.onClick(adapterPosition, list[adapterPosition])
                            }
                        }
                    }
                }
            }
        }
    }

}