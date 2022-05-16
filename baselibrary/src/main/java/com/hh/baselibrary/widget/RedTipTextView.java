package com.hh.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;

import androidx.appcompat.widget.AppCompatTextView;

import com.hh.baselibrary.R;
import com.hh.baselibrary.mvp.BaseApplication;


/**
 * Created by hHui on 2020/4/20 0020.
 * <p>
 * 右上角显示红点的textView
 */
public class RedTipTextView extends AppCompatTextView {
    public static final int RED_TIP_INVISIBLE = 0;
    public static final int RED_TIP_VISIBLE = 1;
    public static final int RED_TIP_GONE = 2;
    private int tipVisibility = 0;
    private boolean choice;

    public RedTipTextView(Context context) {
        super(context);
        init(null);
    }

    public RedTipTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RedTipTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RedTipTextView);
            tipVisibility = array.getInt(R.styleable.RedTipTextView_redTipsVisibility, 0);
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tipVisibility == 1) {
            int width = getWidth();
            int paddingRight = getPaddingRight();
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(width - getPaddingRight() / 2, paddingRight / 2, paddingRight / 2, paint);
        }
    }

    public void setRedTipVisibility(int visibility) {
        tipVisibility = visibility;
        invalidate();
    }


    public boolean getChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
        if (choice) {
            this.setPadding(20, 10, 20, 10);
            this.setBackgroundColor(BaseApplication.Companion.getLogoColor());
            this.setTextColor(Color.WHITE);
        } else {
            this.setPadding(20, 10, 20, 10);
            this.setBackgroundColor(getContext().getResources().getColor(R.color.gray_btn_bg_color));
            this.setTextColor(Color.BLACK);
        }
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
    }
}
