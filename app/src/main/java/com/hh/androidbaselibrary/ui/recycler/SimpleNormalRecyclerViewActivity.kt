package com.hh.androidbaselibrary.ui.recycler

import android.os.Bundle
import android.widget.TextView
import com.hh.androidbaselibrary.R
import com.hh.androidbaselibrary.test.Person
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.util.common.RecyclerItemElementClickListener
import com.hh.baselibrary.util.common.RecyclerViewItemClickListener
import com.hh.baselibrary.widget.recycler.NormalRecyclerView
import kotlinx.android.synthetic.main.activity_simple_normal_recycler_view.*

class SimpleNormalRecyclerViewActivity : BaseActivity() {
    private val list = ArrayList<Person>()
    private val adapter = SimpleAdapter(this, ArrayList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_normal_recycler_view)

        initView()
    }

    override fun initView() {
        //插件获取的view不带泛型需要转换不然会报类型异常
        val mRecyclerView = test_rec as NormalRecyclerView<Person>
        val noDataView = TextView(this)
        noDataView.text = "没有数据怎么显示啊"
        mRecyclerView.init(adapter)
        btn_show.setOnClickListener {
            for (i in 0..10) {
                list.add(Person("name_$i", i + 10))
            }
            mRecyclerView.refreshAll(list)
        }
        btn_clear.setOnClickListener {
            list.clear()
            mRecyclerView.refreshAll(list)
        }

        mRecyclerView.recyclerViewItemClickListener =
            object : RecyclerViewItemClickListener<Person> {
                override fun onClick(position: Int, data: Person) {
                    showToast("点击了第$position 项")
                }
            }
        mRecyclerView.addElementClickListener(object : RecyclerItemElementClickListener<Person> {
            override fun onClick(id: Int, position: Int, data: Person) {
                when (id) {
                    R.id.name -> showToast("点击了姓名 ${data.name}")
                    R.id.age -> showToast("点击了年龄 ${data.age}")
                }
            }
        }, R.id.name)
    }

    override fun transportStatusBar(): Boolean = false
}

