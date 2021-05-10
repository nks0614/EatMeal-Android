package com.project.eatmeal.ui.signup

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.databinding.ActivitySignUpBinding
import com.project.simplecode.spCheckEmpty
import com.project.simplecode.spaToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SignUpActivity : BindingActivity<ActivitySignUpBinding>() {

    private val viewModel : SignUpViewModel by lazy {
        getViewModel(SignUpViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun observeEvent() {
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
            return spCheckEmpty(idText.value!!, pwText.value!!, pwCheckText.value!!, nameText.value!!, classText.value!!)
        }
    }


}