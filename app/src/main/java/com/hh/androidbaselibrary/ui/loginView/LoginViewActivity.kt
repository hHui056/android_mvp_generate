package com.hh.androidbaselibrary.ui.loginView

import android.os.Bundle
import com.hh.androidbaselibrary.R
import com.hh.baselibrary.mvp.BaseActivity
import com.hh.baselibrary.ui.LoginView
import kotlinx.android.synthetic.main.activity_login_view.*

class LoginViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)

        initView()
    }

    override fun initView() {
        login_view.listener = object : LoginView.LoginViewListener {
            override fun onLoginClick(userName: String?, password: String?) {
                showToast("点击登录【$userName,$password】")
            }

            override fun onForgetClick() {
                showToast("点击忘记密码")
            }

            override fun onRegisterClick() {
                showToast("点击用户注册")
            }
        }
    }

    override fun transportStatusBar(): Boolean {
        return false
    }
}