package com.hh.androidbaselibrary

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.hh.androidbaselibrary.test.Person
import com.hh.baselibrary.util.ToastUtil
import com.hh.baselibrary.widget.option.OptionItemChoiceListener
import com.hh.baselibrary.widget.option.ShowOptionUtil
import kotlinx.android.synthetic.main.activity_main.*
@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {

    val optionUtil = ShowOptionUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        show.setOnClickListener {
            val list = ArrayList<Person>()
            for (i in 0..10) {
                list.add(Person("李$i", 0))
            }

            optionUtil.showOptionChoice(list,"",object :OptionItemChoiceListener{
                override fun onChoice(position: Int) {
                    ToastUtil.showToast(this@MainActivity,"$position")
                }
            })
        }
    }
}
