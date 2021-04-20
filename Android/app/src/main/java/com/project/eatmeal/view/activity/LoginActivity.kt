package com.project.eatmeal.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseActivity
import com.project.eatmeal.databinding.ActivityLoginBinding
import com.project.eatmeal.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun init() {

    }

    override fun observerViewModel() {

    }

}