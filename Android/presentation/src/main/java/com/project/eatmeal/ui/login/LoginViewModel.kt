package com.project.eatmeal.ui.login

import androidx.lifecycle.MutableLiveData
import com.project.domain.usecase.LoginUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.widget.SingleLiveEvent

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val idText : MutableLiveData<String> = MutableLiveData("")
    val pwText : MutableLiveData<String> = MutableLiveData("")
    val checked : MutableLiveData<Boolean> = MutableLiveData(false)

    val signUpBtn = SingleLiveEvent<Unit>()
    val signInBtn = SingleLiveEvent<Unit>()

    val isGetLoginData = MutableLiveData<Boolean>(false)
    var status : Int = 0

    fun login() {

    }

    fun signUpBtnCall() = signUpBtn.call()
    fun signInBtnCall() = signInBtn.call()


}