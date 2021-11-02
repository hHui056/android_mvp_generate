package com.hh.baselibrary.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.hh.baselibrary.R

/**
 *
 * Created by hHui on 2019/12/10 0010.
 *
 */
class LoadingDialog {
    var dialog: Dialog? = null

    private lateinit var tipTextView: TextView
    private lateinit var cancelButton: MaterialButton

    var isShowing: Boolean = false
        get() {
            return dialog!!.isShowing
        }

    fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.dialog_layout, null)// 得到加载view
        val layout = v.findViewById<View>(R.id.dialog_loading_view) as LinearLayout// 加载布局
        tipTextView = v.findViewById<View>(R.id.tipTextView) as TextView// 提示文字
        cancelButton = v.findViewById(R.id.progress_cancel) //取消按钮
        dialog = Dialog(context, R.style.MyDialogStyle)// 创建自定义样式dialog
        dialog?.setCancelable(false) // 是否可以按“返回键”消失
        dialog?.setCanceledOnTouchOutside(false) // 点击加载框以外的区域
        dialog?.setContentView(layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT))// 设置布局
        /**
         * 将显示Dialog的方法封装在这里面
         */
        val window = dialog?.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
    }

    /**
     * 显示loading
     */
    fun showLoading(msg: String) {
        cancelButton.visibility = View.GONE
        tipTextView.text = msg
        dialog?.show()
    }

    /**
     * 可取消的dialog
     */
    fun showLoadingCanCancel(msg:String,cancelText:String,callback:MyAlertDialog.CancelClickBack){
        tipTextView.text = msg
        cancelButton.text = cancelText
        cancelButton.visibility  = View.VISIBLE
        cancelButton.setOnClickListener {
            callback.onCancel()
            closeProgress()
        }
        dialog?.show()
    }

    /**
     * 关闭dialog
     *
     */
    fun closeProgress() {
        if (dialog != null && dialog!!.isShowing) {
            dialog?.dismiss()
        }
    }
}