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
 * 可监听项的点击事件和项中元素的点击事件
 */
class NormalRecyclerView<T> : RelativeLayout {
    private lateinit var adapter: MBaseAdapter<T>
    private var noDataView: View? = null
    private lateinit var view: View

    //是否显示每一列的分割线
    private var showItemDecoration = false

    //显示为网格是每一行显示个数
    private var girdNum = 4

    //显示方式 0-列表 1-网格
    private var showType = 0

    //整个项的点击事件
    var recyclerViewItemClickListener: RecyclerViewItemClickListener<T>? = null
        set(value) {
            field = value
            adapter.recyclerViewItemClickListener = value
        }


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
    ) {
        initStyle(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initStyle(context, attrs)
    }

    init {
        initView()
    }

    /**
     * 初始化
     * @param noDataView 无数据时显示（可为null）
     */
    fun init(adapter: MBaseAdapter<T>, noDataView: View? = null) {
        this.adapter = adapter
        this.noDataView = noDataView
        if (noDataView != null) layout_no_data.addView(noDataView)
        if (showType == 0) {
            val layoutManager = LinearLayoutManager(context)
            m_recycler.layoutManager = layoutManager
            if (showItemDecoration) m_recycler.addItemDecoration(
                DividerItemDecoration(
                    context, layoutManager.orientation
                )
            )
        } else {
            val layoutManager = GridLayoutManager(context, girdNum)
            m_recycler.layoutManager = layoutManager
            m_recycler.adapter = adapter
        }
        m_recycler.adapter = this.adapter
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.nomal_recycler_layout, this)
    }

    private fun initStyle(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NormalRecyclerView)
        this.girdNum = typedArray.getInt(R.styleable.NormalRecyclerView_gridLineNumber, 3)
        this.showType = typedArray.getInt(R.styleable.NormalRecyclerView_showType, 0)
        this.showItemDecoration =
            typedArray.getBoolean(R.styleable.NormalRecyclerView_showItemDecoration, false)

        typedArray.recycle()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAll(list: List<T>) {
        adapter.list = list
        adapter.notifyDataSetChanged()

        m_recycler.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
        this.noDataView?.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

    fun addElementClickListener(listener: RecyclerItemElementClickListener<T>, vararg ids: Int) {
        adapter.recyclerItemElementClickListener = listener
        val idList = ArrayList<Int>()
        for (item in ids) {
            idList.add(item)
        }
        adapter.listenerIds = idList.toTypedArray()
    }
}