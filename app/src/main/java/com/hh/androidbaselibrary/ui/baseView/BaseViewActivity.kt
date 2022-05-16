package com.hh.androidbaselibrary.ui.baseView

import android.os.Bundle
import com.hh.androidbaselibrary.R
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.util.RxTimerUtil
import com.hh.baselibrary.widget.MyAlertDialog
import kotlinx.android.synthetic.main.activity_alert_dialog.*

class BaseViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog)
        initView()
    }

    override fun initView() {
        success.setOnClickListener { alertSuccess("成功") }
        fail.setOnClickListener { alertError("提示", "失败") }
        loading.setOnClickListener {
            showProgress()
            RxTimerUtil().interval(1000, object : RxTimerUtil.IRxNext {
                override fun doNext(number: Long) {
                    if (number == 3L) {
                        closeProgress()
                    }
                }
            })
        }
        can_cancel_loading.setOnClickListener {
            showProgressCanCle(msg = "加载中", cancelText = "cancel", callback = object : MyAlertDialog.CancelClickBack {
                override fun onCancel() {
                    showToast("点击了取消")
                }
            })
        }

        one_button.setOnClickListener {
            alertOneButtonOption("提示", "只有确定按钮！", object : MyAlertDialog.ClickBack {
                override fun onClick() {
                    showToast("点击了确定")
                }
            })
        }

        option.setOnClickListener {
            alertOption("提示", "确定删除？", object : MyAlertDialog.AlertClickBack {
                override fun onConfirm() {
                    showToast("确定")
                }

                override fun onCancel() {
                    showToast("取消")
                }
            })
        }
    }

    override fun transportStatusBar(): Boolean {
        return false
    }
}