package com.hh.baselibrary.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.hh.baselibrary.R
import com.hh.baselibrary.mvp.BaseApplication
import kotlinx.android.synthetic.main.login_view_layout.view.*

/**
 * Create By hHui on 2021/11/2 0002 下午 14:27
 *
 * 通用的登录页面
 * 可修改APP图标、字体颜色、用户名hint、密码hint、登录按钮文字
 */
class LoginView : LinearLayout {

    /**
     * 页面事件监听
     */
    var listener: LoginViewListener? = null

    /**
     * 登录页面显示的logo图标
     */
    @DrawableRes
    var icon: Int = R.drawable.ic_launcher
        set(value) {
            field = value
            login_logo.setImageResource(value)
        }

    /**
     * 用户名提示文字
     */
    var userNameHint: String = "请输入用户名"
        set(value) {
            field = value
            user_name.hint = value
        }

    /**
     * 密码提示文字
     */
    var passwordHint: String = "请输入密码"
        set(value) {
            field = value
            password.hint = value
        }

    /**
     * 登录按钮背景颜色
     */
    @ColorRes
    var loginButtonBackgroundColor: Int = BaseApplication.logoColor
        set(value) {
            field = value
            btn_login.setBackgroundColor(value)
        }

    /**
     * 登录按钮字体颜色
     */
    @ColorRes
    var loginButtonTextColor: Int = R.color.white
        set(value) {
            field = value
            btn_login.setTextColor(value)
        }

    /**
     * 登录按钮文字
     */
    var loginButtonText: String = "登录"
        set(value) {
            field = value
            btn_login.text = value
        }

    /**
     * 忘记密码按钮文字
     */
    var forgetButtonText: String = "忘记密码"
        set(value) {
            field = value
            forget_password.text = value
        }

    /**
     * 新用户注册按钮文字
     */
    var newRegisterButtonText: String = "新用户注册"
        set(value) {
            field = value
            new_register.text = value
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
        inflater.inflate(R.layout.login_view_layout, this)
        //初始化
        btn_login.setBackgroundColor(BaseApplication.logoColor)

        btn_login.setOnClickListener {
            listener?.onLoginClick(user_name.content, password.content)
        }

        forget_password.setOnClickListener { listener?.onForgetClick() }
        new_register.setOnClickListener { listener?.onRegisterClick() }
    }

    private fun initText(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginView)
        val supportRegister = typedArray.getBoolean(R.styleable.LoginView_supportRegister, true)
        val loginButtonBackgroundColor = typedArray.getColor(
            R.styleable.LoginView_loginButtonBackgroundColor,
            BaseApplication.logoColor
        )
        val loginButtonTextColor =
            typedArray.getColor(R.styleable.LoginView_loginButtonTextColor, R.color.white)
        val userNameHint = typedArray.getText(R.styleable.LoginView_userNameHint)
        val passwordHint = typedArray.getText(R.styleable.LoginView_passwordHint)
        val loginButtonText = typedArray.getText(R.styleable.LoginView_loginButtonText)
        val forgetButtonText = typedArray.getText(R.styleable.LoginView_forgetButtonText)
        val newRegisterButtonText = typedArray.getText(R.styleable.LoginView_newRegisterButtonText)
        val iconDrawable = typedArray.getDrawable(R.styleable.LoginView_icon)
        typedArray.recycle() //释放资源

        new_register.visibility = if (supportRegister) View.VISIBLE else View.GONE
        this.loginButtonBackgroundColor = loginButtonBackgroundColor
        this.loginButtonTextColor = loginButtonTextColor
        if (userNameHint != null) this.userNameHint = userNameHint.toString()
        if (passwordHint != null) this.passwordHint = passwordHint.toString()
        if (loginButtonText != null) this.loginButtonText = loginButtonText.toString()
        if (forgetButtonText != null) this.forgetButtonText = forgetButtonText.toString()
        if (newRegisterButtonText != null) this.newRegisterButtonText =
            newRegisterButtonText.toString()
        if (iconDrawable != null) {
            login_logo.setImageDrawable(iconDrawable)
        }
    }

    interface LoginViewListener {
        /**
         * 点击登录
         */
        fun onLoginClick(userName: String?, password: String?)

        /**
         * 点击忘记密码
         */
        fun onForgetClick()

        /**
         * 点击新用户注册
         */
        fun onRegisterClick()
    }
}