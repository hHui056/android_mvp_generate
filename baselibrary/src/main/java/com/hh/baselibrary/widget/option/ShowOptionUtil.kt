package com.hh.baselibrary.widget.option

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.hh.baselibrary.R
import com.hh.baselibrary.exception.HHException
import com.hh.baselibrary.mvp.BaseApplication
import java.lang.reflect.Method


/**
 * Created by hHui on 2020/5/15 0015.
 *
 *显示底部选项框工具类
 */
@RequiresApi(Build.VERSION_CODES.N)
class ShowOptionUtil(val context: Context) {
    private var pvOptions: OptionsPickerView<String>? = null

    @Throws
    fun <T> showOptionChoice(data: List<T>, title: String, listener: OptionItemChoiceListener<T>) {
        //条件选择器
        pvOptions = OptionsPickerBuilder(context, OnOptionsSelectListener { options1, _, _, _ ->
            //返回的分别是三个级别的选中位置
            listener.onChoice(options1, data[options1])
        }).setLayoutRes(R.layout.option_choice_layout) { v ->
            val tvSubmit = v.findViewById<TextView>(R.id.tv_finish)
            val tvCancel = v.findViewById<TextView>(R.id.tv_cancel)
            val tvTitle = v.findViewById<TextView>(R.id.tv_title)
            tvCancel.setTextColor(Color.RED)
            tvSubmit.setTextColor(BaseApplication.logoColor)
            tvTitle.setTextColor(BaseApplication.logoColor)
            tvTitle.text = title
            tvSubmit.setOnClickListener {
                pvOptions!!.returnData()
                pvOptions!!.dismiss()
            }
            tvCancel.setOnClickListener { pvOptions!!.dismiss() }
        }.isDialog(false).setOutSideCancelable(false).build()
        val list = ArrayList<String>()
        data.forEach {
            val str = getShowString(it!!)
            if (str == null) {
                throw HHException("待显示的类属性必须包含@ShowString注解")
            } else {
                list.add(str)
            }
        }
        pvOptions!!.setPicker(list)
        pvOptions!!.show()
    }


    private fun getShowString(obj: Any): String? {
        try {
            val fieldList = obj.javaClass.declaredFields.toList()
            fieldList.forEach {
                val showString = it.getAnnotation(ShowString::class.java)
                if (showString == null) { //该属性没有该注解
                    return@forEach
                } else {
                    return getFieldValueByName(it.name, obj)
                }
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }


    private fun getFieldValueByName(fieldName: String, o: Any): String? {
        return try {
            val firstLetter = fieldName.substring(0, 1).toUpperCase()
            val getter = "get" + firstLetter + fieldName.substring(1)
            val method: Method = o.javaClass.getMethod(getter)
            val value: Any = method.invoke(o)
            value.toString()
        } catch (e: java.lang.Exception) {
            null
        }
    }

}