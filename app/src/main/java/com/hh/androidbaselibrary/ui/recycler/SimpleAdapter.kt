package com.hh.androidbaselibrary.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hh.androidbaselibrary.R
import com.hh.androidbaselibrary.test.Person
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.util.common.RecyclerViewItemClickListener
import com.hh.baselibrary.widget.recycler.MBaseAdapter

/**
 * Create By hHui on 2022/5/9 0009 下午 15:51
 */
class SimpleAdapter(private val activity: BaseActivity, mList: List<Person>) :
    MBaseAdapter<Person>(mList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MBaseViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.person_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MBaseViewHolder, position: Int) {
        val mHolder = holder as ViewHolder
        val a = list[position]
        mHolder.name.text = a.name
        mHolder.age.text = a.age.toString()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val mview: View) : MBaseViewHolder(mview) {
        var name = view.findViewById<TextView>(R.id.name)
        var age = view.findViewById<TextView>(R.id.age)
    }


}