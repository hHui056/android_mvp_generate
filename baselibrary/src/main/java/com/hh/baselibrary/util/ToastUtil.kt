package com.hh.baselibrary.util

import android.content.Context
import android.widget.Toast

/**
 * Created by hHui on 2019/12/10 0010.
 */
object ToastUtil {
    private var toast: Toast? = null

    fun showToast(context: Context, str: String) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
            //toast!!.setGravity(Gravity.CENTER, 0, 0)   //设置toast的位置
        } else {
            toast!!.setText(str)
        }
        toast?.show()
    }
}