package com.hh.baselibrary.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.text.InputFilter
import com.hh.baselibrary.R
import com.hh.baselibrary.util.expend.getString
import com.hh.baselibrary.util.expend.showText
import kotlinx.android.synthetic.main.my_edit_layout.view.*


/**
 * Created by hHui on 2019/11/29 0029.
 *
 * 自定义输入框 支持左边图片 右边按钮
 */
class MyEditText : LinearLayout {
    /**
     * 输入框文字
     */
    var content: String? = null
        set(value) {
            field = value
            edit_content.showText(value)
        }
        get() {
            return edit_content.getString()
        }

    /**
     * 按钮文字
     */
    var buttonText: String = ""
        set(value) {
            field = value
            btn_right.text = value
        }
        get() {
            return btn_right.text.toString()
        }

    /**
     * 是否明文显示密码
     */
    var isShowPassWord: Boolean = false
        set(value) {
            field = value
            if (value) {
                edit_content.typeface = Typeface.DEFAULT
                edit_content.transformationMethod = HideReturnsTransformationMethod.getInstance() //明文显示密码
                edit_content.setSelection(edit_content.length())
                btn_right.setBackgroundResource(R.drawable.eye_open)
            } else {
                edit_content.typeface = Typeface.DEFAULT
                edit_content.transformationMethod = PasswordTransformationMethod.getInstance()
                edit_content.setSelection(edit_content.length())
                btn_right.setBackgroundResource(R.drawable.eye_close)
            }
        }


    /**
     * 右边按钮点击事件
     */
    var rightButtonClickListener: RightButtonClickListener? = null

    /**
     * 右边的按钮是否可点击
     */
    var rightButtonCanClick: Boolean = true
        set(value) {
            field = value
            btn_right.isClickable = value
        }


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initTextAndImage(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initTextAndImage(context, attrs)
    }

    init {
        initView()
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.my_edit_layout, this)

        btn_right.setOnClickListener { rightButtonClickListener?.onClick() }
    }

    @SuppressLint("Recycle")
    private fun initTextAndImage(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditText)
        val leftImageDrawable = typedArray.getDrawable(R.styleable.MyEditText_leftImage)
        //左边图片
        if (leftImageDrawable == null) {
            img_left.visibility = View.GONE
        } else {
            img_left.setImageDrawable(leftImageDrawable)
        }
        //右边按钮文字
        var rightText = ""
        if (typedArray.getText(R.styleable.MyEditText_rightText) != null) {
            rightText = typedArray.getText(R.styleable.MyEditText_rightText).toString()
        }
        if (rightText != "") {
            btn_right.text = rightText
        }
        //右边按钮图片
        val rightImageDrawable = typedArray.getDrawable(R.styleable.MyEditText_rightImage)
        if (rightImageDrawable == null) {
            btn_right.setBackgroundResource(R.drawable.shape_corner)
        } else {
            btn_right.background = rightImageDrawable
        }

        btn_right.visibility = if (rightText != "" || rightImageDrawable != null) View.VISIBLE else View.GONE

        //提示文字
        var hintText = ""
        if (typedArray.getText(R.styleable.MyEditText_hint) != null) {
            hintText = typedArray.getText(R.styleable.MyEditText_hint).toString()
        }
        edit_content.setHintTextColor(Color.GRAY)
        edit_content.hint = hintText

        val isNumber = typedArray.getBoolean(R.styleable.MyEditText_isNumber, false)
        if (isNumber) edit_content.inputType = InputType.TYPE_CLASS_NUMBER

        val isPassWord = typedArray.getBoolean(R.styleable.MyEditText_isPassword, false)
        if (isPassWord) {
            edit_content.typeface = Typeface.DEFAULT
            edit_content.transformationMethod = PasswordTransformationMethod.getInstance()

            rightButtonClickListener = object : RightButtonClickListener {
                override fun onClick() {
                    isShowPassWord = !isShowPassWord
                }
            }
        }

        //最大长度
        val maxLength = typedArray.getInt(R.styleable.MyEditText_maxSize, 0)
        if (maxLength != 0) {
            edit_content.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        }
    }

    interface RightButtonClickListener {
        fun onClick()
    }
}