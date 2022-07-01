package com.hh.baselibrary.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.hh.baselibrary.R
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.mvp.BaseApplication
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
                layout_right_text.visibility = View.VISIBLE
            } else {
                layout_right_text.visibility = View.GONE
            }
        }
        get() {
            return txt_right.text.toString()
        }

    /**
     * 顶部导航栏背景颜色
     */
    @SuppressLint("ResourceAsColor")
    @ColorInt
    var titleBackgroundColor: Int = BaseApplication.logoColor
        set(value) {
            field = value
            layout_title.setBackgroundColor(value)
        }

    /**
     * 顶部导航栏文字颜色（包含左边、中间、右边）
     */
    @SuppressLint("ResourceAsColor")
    @ColorInt
    var titleTextColor: Int = R.color.white
        set(value) {
            field = value
            txt_left.setTextColor(value)
            m_title.setTextColor(value)
            txt_right.setTextColor(value)
        }

    /**
     * 右边图片1点击事件监听
     */
    var titleRightImage1ClickListener: TitleRightClick? = null

    /**
     * 右边图片2点击事件监听
     */
    var titleRightImage2ClickListener: TitleRightClick? = null

    /**
     * 右边文字点击事件监听
     */
    var titleRightTextClickListener: TitleRightClick? = null


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
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
        inflater.inflate(R.layout.title_layout, this)

        layout_back.setOnClickListener {
            val activity = context as BaseActivity
            activity.miss()
        }

        layout_right1.setOnClickListener { titleRightImage1ClickListener?.onClick() }
        layout_right2.setOnClickListener { titleRightImage2ClickListener?.onClick() }
        layout_right_text.setOnClickListener { titleRightTextClickListener?.onClick() }
    }

    @SuppressLint("ResourceAsColor")
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
        val rightImageDrawable1 = typedArray.getDrawable(R.styleable.TitleView_rightImg1)
        val rightImageDrawable2 = typedArray.getDrawable(R.styleable.TitleView_rightImg2)
        val backImageDrawable = typedArray.getDrawable(R.styleable.TitleView_backImg)
        val titleBackgroundColor = typedArray.getColor(
            R.styleable.TitleView_titleBackgroundColor, BaseApplication.logoColor
        )
        val titleTextColor =
            typedArray.getColor(R.styleable.TitleView_titleTextColor, R.color.white)
        typedArray.recycle()
        //设置文字颜色和背景颜色
        this.titleBackgroundColor = titleBackgroundColor
        if (titleTextColor != R.color.white) {
            this.titleTextColor = titleTextColor
        }
        //设置标题
        title = mText
        //是否显示返回按钮
        layout_back.visibility = if (isSupportBack) View.VISIBLE else View.GONE
        //右边图片1
        if (rightImageDrawable1 == null) {
            layout_right1.visibility = View.GONE
        } else {
            layout_right1.visibility = View.VISIBLE
            img_right1.setImageDrawable(rightImageDrawable1)
        }
        //右边图片2
        if (rightImageDrawable2 == null) {
            layout_right2.visibility = View.GONE
        } else {
            layout_right2.visibility = View.VISIBLE
            img_right2.setImageDrawable(rightImageDrawable2)
        }
        //设置返回的图片（默认为向左的箭头）
        if (backImageDrawable != null) {
            back.setImageDrawable(backImageDrawable)
        }
        //设置右边文字
        if (rightText != null) {
            layout_right_text.visibility = View.VISIBLE
            txt_right.text = rightText
        } else {
            layout_right_text.visibility = View.GONE
        }
    }

    interface TitleRightClick {
        fun onClick()
    }
}