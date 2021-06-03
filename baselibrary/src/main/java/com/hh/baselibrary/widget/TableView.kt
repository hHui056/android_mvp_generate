package com.hh.baselibrary.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hh.baselibrary.R
import kotlinx.android.synthetic.main.layout_table.view.*

/**
 * Created by hHui on 2020/1/8 0008.
 */
class TableView : LinearLayout {

    private lateinit var normalDrawable: Drawable

    private var choiceDrawable: Drawable? = null

    /**
     * 是否为选中状态
     */
    var isChoice: Boolean = false
        set(value) {
            field = value
            if (isChoice) {
                table_img.setImageDrawable(choiceDrawable)
                table_name.setTextColor(resources.getColor(R.color.logoColor))
            } else {
                table_img.setImageDrawable(normalDrawable)
                table_name.setTextColor(Color.GRAY)
            }
        }

    var title: String = ""
        get() {
            return table_name.text.toString().trim()
        }
        set(value) {
            field = value
            table_name.text = value
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initText(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initText(context, attrs)
    }

    init {
        initView()
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_table, this)
    }

    private fun initText(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TableView)
        var mText = ""
        if (typedArray.getText(R.styleable.TableView_table) != null) {
            mText = typedArray.getText(R.styleable.TableView_table).toString()
        }
        normalDrawable = typedArray.getDrawable(R.styleable.TableView_normalImg)!!
        choiceDrawable = typedArray.getDrawable(R.styleable.TableView_choiceImg)
        typedArray.recycle()

        table_name.text = mText
        table_img.setImageDrawable(normalDrawable)
    }
}