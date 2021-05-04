package com.project.eatmeal.view.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseActivity
import com.project.eatmeal.databinding.ActivitySignUpBinding
import com.project.eatmeal.viewmodel.SignUpViewModel
import com.project.simplecode.spaToastShort

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel
        get() = ViewModelProvider(this)[SignUpViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_sign_up

    override fun init() {

    }

    override fun observerViewModel() {
        with(viewModel) {
            duplicateBtn.observe(this@SignUpActivity, Observer {
                if(!idText.value.isNullOrBlank()) {
                    binding.duplicateBtn.isEnabled = false
                    binding.duplicateBtn.showLoading()
                    duplicateID()
                } else {
                    spaToastShort("ID를 제대로 적어주시기 바랍니다.")
                }
            })

            isIdCheck.observe(this@SignUpActivity, Observer {
                binding.duplicateBtn.isEnabled = true
                binding.duplicateBtn.hideLoading()
                if(isIdCheck.value!!) {
                    binding.idCheckInfoText.text = getString(R.string.signup_ok_id)
                    binding.idCheckInfoText.setTextColor(Color.parseColor("#8EED8E"))
                } else {
                    binding.idCheckInfoText.text = getString(R.string.signup_no_id)
                    binding.idCheckInfoText.setTextColor(Color.parseColor("#FF5959"))
                }
            })

            signUpBtn.observe(this@SignUpActivity, Observer {
                if(textBlankCheck()) {
                    if(pwText.value == pwCheckText.value) {
                        if(isIdCheck.value!!) {
                            binding.signUpBtn.isEnabled = false
                            binding.signUpBtn.showLoading()
                            signUp()
                        } else {
                            spaToastShort("ID 중복 확인이 되지 않았습니다.")
                        }
                    } else {
                        spaToastShort("비밀번호가 다릅니다.")
                    }
                } else {
                    spaToastShort("제대로 적었는지 확인해주시기 바랍니다.")
                }
            })

            isSignUp.observe(this@SignUpActivity, Observer {
                binding.signUpBtn.isEnabled = true
                binding.signUpBtn.hideLoading()
                if(isSignUp.value!!) {
                    spaToastShort("회원가입에 성공하였습니다!")
                    finish()
                } else {
                    spaToastShort("회원가입에 실패했습니다.")
                }
            })
        }
    }

    private fun textBlankCheck() : Boolean {
        with(viewModel) {
            if(!idText.value.isNullOrBlank() && !pwText.value.isNullOrBlank()
                && !pwCheckText.value.isNullOrBlank() && !nameText.value.isNullOrBlank()
                && !classText.value.isNullOrBlank()) {
                return true
            }
        }
        return false
    }


}