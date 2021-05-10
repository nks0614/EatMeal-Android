package com.project.eatmeal.ui.login

import android.os.Bundle
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity : BindingActivity<ActivityLoginBinding>() {

    private val viewModel : LoginViewModel by lazy {
        getViewModel(LoginViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun observeEvent() {

    }


}