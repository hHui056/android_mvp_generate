package com.hh.baselibrary.widget.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hh.baselibrary.R
import com.hh.baselibrary.util.common.RecyclerItemElementClickListener
import com.hh.baselibrary.util.common.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.nomal_recycler_layout.view.*

/**
 * Create By hHui on 2022/5/9 0009 下午 15:03
 *
 * 封装RecyclerView包含无数据的提示
 */
class NormalRecyclerView<T> : RelativeLayout {
    private lateinit var adapter: MBaseAdapter<T>
    private lateinit var noDataView: View
    private lateinit var view: View

    //是否显示每一列的分割线
    private var showItemDecoration = false

    //显示为网格是每一行显示个数
    private var girdNum = 4

    //整个项的点击事件
    var recyclerViewItemClickListener: RecyclerViewItemClickListener<T>? = null
        set(value) {
            field = value
            adapter.recyclerViewItemClickListener = value
        }

    //元素的点击事件
    var recyclerItemElementClickListener: RecyclerItemElementClickListener<T>? = null
        set(value) {
            field = value
            adapter.recyclerItemElementClickListener = value
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
    ) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    init {
        initView()
    }

    /**
     * 列表显示
     */
    fun initLinear(adapter: MBaseAdapter<T>, noDataView: View) {
        this.adapter = adapter
        this.noDataView = noDataView
        layout_no_data.addView(noDataView)

        val layoutManager = LinearLayoutManager(context)
        m_recycler.layoutManager = layoutManager
        if (showItemDecoration) m_recycler.addItemDecoration(
            DividerItemDecoration(
                context, layoutManager.orientation
            )
        )
        m_recycler.adapter = this.adapter
    }

    /**
     * 网格显示
     */
    fun initGrid(adapter: MBaseAdapter<T>, noDataView: View) {
        this.adapter = adapter
        this.noDataView = noDataView
        layout_no_data.addView(noDataView)

        val layoutManager = GridLayoutManager(context, girdNum)
        m_recycler.layoutManager = layoutManager
        m_recycler.adapter = adapter
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.nomal_recycler_layout, this)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAll(list: List<T>) {
        adapter.list = list
        adapter.notifyDataSetChanged()

        m_recycler.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
        this.noDataView.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }
}