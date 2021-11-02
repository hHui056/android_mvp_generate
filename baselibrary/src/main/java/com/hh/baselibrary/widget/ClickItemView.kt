package com.hh.baselibrary.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.hh.baselibrary.R
import kotlinx.android.synthetic.main.click_item_layout.view.*

/**
 *
 *Create By hHui on 2021年6月17日15:02
 *
 */
class ClickItemView : LinearLayout {
    /** 项显示文本 **/
    var text: String = ""
        set(value) {
            field = value
            txt_item.text = value
        }

    /** 右边文字 **/
    var rightText: String = ""
        set(value) {
            field = value
            txt_right.text = value
        }

    /** 是否显示右上角红点 **/
    var showRedTip: Boolean = false
        set(value) {
            field = value
            if (value) txt_item.setRedTipVisibility(RedTipTextView.RED_TIP_VISIBLE) else txt_item.setRedTipVisibility(
                RedTipTextView.RED_TIP_GONE
            )
        }

    /**
     * 右侧[ImageView]
     */
    var itemRightImage: ImageView? = null
        get() {
            return item_img_right
        }


    private var itemImageDrawable: Drawable? = null

    private var itemRightImageDrawable: Drawable? = null

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
        inflater.inflate(R.layout.click_item_layout, this)
        layout_item.setBackgroundResource(R.drawable.item_click_background)
    }

    private fun initText(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClickItemView)
        val isTopShow = typedArray.getBoolean(R.styleable.ClickItemView_isTopLineShow, false)
        val isBottomShow = typedArray.getBoolean(R.styleable.ClickItemView_isBottomLineShow, false)
        val isHaveNextPage = typedArray.getBoolean(R.styleable.ClickItemView_haveNextPage, true)
        var mText = ""
        if (typedArray.getText(R.styleable.ClickItemView_itemText) != null) {
            mText = typedArray.getText(R.styleable.ClickItemView_itemText).toString()
        }
        itemImageDrawable = typedArray.getDrawable(R.styleable.ClickItemView_itemImg)
        itemRightImageDrawable = typedArray.getDrawable(R.styleable.ClickItemView_itemRightImg)
        val textColor = typedArray.getColor(R.styleable.ClickItemView_itemTextColor, Color.BLACK)
        typedArray.recycle() //释放资源
        txt_item.text = mText
        txt_item.setTextColor(textColor)
        line_bottom.visibility = if (isBottomShow) View.VISIBLE else View.INVISIBLE
        line_top.visibility = if (isTopShow) View.VISIBLE else View.INVISIBLE
        img_right.visibility = if (isHaveNextPage) View.VISIBLE else View.GONE
        img_item.visibility = if (itemImageDrawable != null) View.VISIBLE else View.GONE
        item_img_right.visibility = if (itemRightImageDrawable != null) View.VISIBLE else View.GONE
        if (itemImageDrawable != null) img_item.setImageDrawable(itemImageDrawable)
        if (itemRightImageDrawable != null) item_img_right.setImageDrawable(itemRightImageDrawable)
    }
}