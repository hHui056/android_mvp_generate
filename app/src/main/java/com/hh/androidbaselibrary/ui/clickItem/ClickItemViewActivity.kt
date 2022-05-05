package com.hh.androidbaselibrary.ui.clickItem

import android.os.Bundle
import com.hh.androidbaselibrary.R
import com.hh.baselibrary.mvp.BaseActivity

class ClickItemViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_item_view)
    }

    override fun initView() {

    }

    override fun transportStatusBar(): Boolean {
        return false
    }
}