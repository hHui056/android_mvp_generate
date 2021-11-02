package com.hh.androidbaselibrary.ui.dialog

import android.os.Bundle
import com.hh.androidbaselibrary.R
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.util.RxTimerUtil
import kotlinx.android.synthetic.main.activity_alert_dialog.*

class AlertDialogActivity : BaseActivity() {

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
    }
}