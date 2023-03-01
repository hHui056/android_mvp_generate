package com.hh.androidbaselibrary

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.hh.androidbaselibrary.bean.Person
import com.hh.androidbaselibrary.ui.baseView.BaseViewActivity
import com.hh.androidbaselibrary.ui.dataStore.view.DataStoreActivity
import com.hh.androidbaselibrary.ui.loginView.LoginViewActivity
import com.hh.androidbaselibrary.ui.objectBox.view.ObjectBoxActivity
import com.hh.androidbaselibrary.ui.recycler.SimpleNormalRecyclerViewActivity
import com.hh.androidbaselibrary.ui.registerView.RegisterViewActivity
import com.hh.androidbaselibrary.ui.tab.TabLayoutActivity
import com.hh.androidbaselibrary.ui.titleView.TitleViewActivity
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.mvp.BaseApplication
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

            optionUtil.showOptionChoice(list, "", object : OptionItemChoiceListener<Person> {
                override fun onChoice(position: Int, data: Person) {
                    ToastUtil.showToast(this@MainActivity, "$position")
                }
            })
        }
        item_dialog.rightText = "右边文字"
        item_dialog.rightTextColor = resources.getColor(R.color.red_btn_bg_color)

        item_title_view.setOnClickListener { jump2Activity(TitleViewActivity::class.java, false) }
        item_login_view.setOnClickListener { jump2Activity(LoginViewActivity::class.java, false) }
        item_dialog.setOnClickListener { jump2Activity(BaseViewActivity::class.java, false) }
        tab_view_pager.setOnClickListener { jump2Activity(TabLayoutActivity::class.java, false) }
        tab_data_store.setOnClickListener { jump2Activity(DataStoreActivity::class.java, false) }

        tab_object_box.setOnClickListener { jump2Activity(ObjectBoxActivity::class.java, false) }
        tab_recycler.setOnClickListener {
            jump2Activity(
                SimpleNormalRecyclerViewActivity::class.java,
                false
            )
        }
        item_register_view.setOnClickListener {
            jump2Activity(
                RegisterViewActivity::class.java, false
            )
        }
        tab_restart.setOnClickListener {
            BaseApplication.instance.closeAllActivities()
            val mIntent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(mIntent)
        }
    }

    override fun initView() {
    }

    override fun transportStatusBar(): Boolean {
        return false
    }
}
