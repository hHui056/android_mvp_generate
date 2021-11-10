package com.hh.androidbaselibrary

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.hh.androidbaselibrary.test.Person
import com.hh.androidbaselibrary.ui.baseView.BaseViewActivity
import com.hh.androidbaselibrary.ui.loginView.LoginViewActivity
import com.hh.androidbaselibrary.ui.registerView.RegisterViewActivity
import com.hh.androidbaselibrary.ui.titleView.TitleViewActivity
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.util.ToastUtil
import com.hh.baselibrary.widget.option.OptionItemChoiceListener
import com.hh.baselibrary.widget.option.ShowOptionUtil
import kotlinx.android.synthetic.main.activity_main.*

@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : BaseActivity() {
    private val optionUtil = ShowOptionUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show_option.setOnClickListener {
            val list = ArrayList<Person>()
            for (i in 0..10) {
                list.add(Person("选项$i", 0))
            }

            optionUtil.showOptionChoice(list, "", object : OptionItemChoiceListener {
                override fun onChoice(position: Int) {
                    ToastUtil.showToast(this@MainActivity, "$position")
                }
            })
        }

        item_title_view.setOnClickListener { jump2Activity(TitleViewActivity::class.java, false) }
        item_login_view.setOnClickListener { jump2Activity(LoginViewActivity::class.java, false) }
        item_dialog.setOnClickListener { jump2Activity(BaseViewActivity::class.java, false) }

        item_register_view.setOnClickListener { jump2Activity(RegisterViewActivity::class.java,false) }
    }

    override fun initView() {
    }
}
