package com.hh.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.hh.baselibrary.R
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.util.common.OnMultiClickListener
import kotlinx.android.synthetic.main.title_layout.view.*
import java.lang.Exception

/**
 * Created by hHui on 2020/1/15 0015.
 *
 * 自定义顶部Title
 */
class TitleView : LinearLayout {
    /**
     * 中间title文字
     */
    var title: String = ""
        set(value) {
            field = value
            m_title.text = value
        }

    /**
     * 左边文字
     */
    var leftText: String = ""
        set(value) {
            field = value
            txt_left.text = value
            if (value != "") {
                txt_left.visibility = View.VISIBLE
            } else {
                txt_left.visibility = View.GONE
            }
        }
        get() {
            return txt_left.text.toString()
        }

    /**
     * 右边文字
     */
    var rightText: String = ""
        set(value) {
            field = value
            txt_right.text = value
            if (value != "") {
                txt_right.visibility = View.VISIBLE
            } else {
                txt_right.visibility = View.GONE
            }
        }
        get() {
            return txt_right.text.toString()
        }

    /**
     * 右边图片点击事件监听
     */
    var titleRightImageClickListener: TitleRightClick? = null

    /**
     * 右边文字点击事件监听
     */
    var titleRightTextClickListener: TitleRightClick? = null

    /**
     * 是否显示右边的图片
     */
    var showRightImage: Boolean = false
        set(value) {
            field = value
            img_right.visibility = if (value) View.VISIBLE else View.GONE
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
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
        inflater.inflate(R.layout.title_layout, this)

        back.setOnClickListener {
            val activity = context as BaseActivity
            activity.miss()
        }

        img_right.setOnClickListener{ titleRightImageClickListener?.onClick()}

        txt_right.setOnClickListener { titleRightTextClickListener?.onClick() }


    }

    private fun initText(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
        //标题
        var mText = ""
        if (typedArray.getText(R.styleable.TitleView_title) != null) {
            mText = typedArray.getText(R.styleable.TitleView_title).toString()
        }
        val rightText = try {
            typedArray.getText(R.styleable.TitleView_titleRightText).toString()
        } catch (e: Exception) {
            null
        }
        val isSupportBack = typedArray.getBoolean(R.styleable.TitleView_isSupportBack, false)
        val rightImageDrawable = typedArray.getDrawable(R.styleable.TitleView_rightImg)
        val backImageDrawable = typedArray.getDrawable(R.styleable.TitleView_backImg)
        typedArray.recycle()
        //设置标题
        title = mText
        //是否显示返回按钮
        back.visibility = if (isSupportBack) View.VISIBLE else View.GONE
        //右边图片
        if (rightImageDrawable == null) {
            img_right.visibility = View.GONE
        } else {
            img_right.visibility = View.VISIBLE
            img_right.setImageDrawable(rightImageDrawable)
        }
        //设置返回的图片（默认为向左的箭头）
        if (backImageDrawable != null) {
            back.setImageDrawable(backImageDrawable)
        }
        //设置右边文字
        if (rightText != null) {
            txt_right.visibility = View.VISIBLE
            txt_right.text = rightText
        } else {
            txt_right.visibility = View.GONE
        }
    }

    interface TitleRightClick {
        fun onClick()
    }
}