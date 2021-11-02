package com.hh.androidbaselibrary.ui.titleView

import android.os.Bundle
import com.hh.androidbaselibrary.R
import com.hh.baselibrary.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_title_view.*

class TitleViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_view)

        initView()
    }

    override fun initView() {

        title_test.leftText = "左侧文字"
    }
}