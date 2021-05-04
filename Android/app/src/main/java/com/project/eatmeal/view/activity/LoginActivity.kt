package com.project.eatmeal.view.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseActivity
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.ActivityLoginBinding
import com.project.eatmeal.viewmodel.LoginViewModel
import com.project.eatmeal.widget.EatMealApplication
import com.project.simplecode.spaIntent
import com.project.simplecode.spaIntentNoFin
import com.project.simplecode.spaToastLong
import okhttp3.internal.cache2.Relay.Companion.edit

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun init() {


    }

    override fun observerViewModel() {
        with(viewModel) {
            signInBtn.observe(this@LoginActivity, Observer {
                if(!(idText.value.isNullOrBlank() || pwText.value.isNullOrBlank())){
                    binding.signInBtn.showLoading()
                    binding.signInBtn.isEnabled = false
                    login()
                } else  {
                    spaToastLong("ID 또는 비밀번호가 입력되지 않았습니다.")
                }
            })

            signUpBtn.observe(this@LoginActivity, Observer {
                spaIntentNoFin(SignUpActivity::class.java)
            })

            isGetLoginData.observe(this@LoginActivity, Observer {
                if(status == 0) return@Observer
                binding.signInBtn.hideLoading()
                binding.signInBtn.isEnabled = true
                if(status == 200){
                    spaToastLong("로그인에 성공하였습니다.")
                    if(binding.autoLoginCheckBox.isChecked) {
                        val sharedPref = getSharedPreferences(EatMealApplication.SHARED, Context.MODE_PRIVATE).edit()
                        sharedPref.putString(EatMealApplication.ID, idText.value)
                        sharedPref.putString(EatMealApplication.PW, pwText.value)
                        sharedPref.apply()
                    }
                    spaIntent(MainActivity::class.java)
                } else {
                    spaToastLong("로그인에 실패하였습니다. ${status}")
                }
            })


        }
    }

}