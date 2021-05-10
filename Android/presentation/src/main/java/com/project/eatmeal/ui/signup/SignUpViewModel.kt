package com.project.eatmeal.ui.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.domain.usecase.IdCheckUseCase
import com.project.domain.usecase.SignUpUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.widget.SingleLiveEvent


class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val idCheckUseCase: IdCheckUseCase
) : BaseViewModel() {

    val idText = MutableLiveData<String>("")
    val pwText = MutableLiveData<String>("")
    val pwCheckText = MutableLiveData<String>("")
    val nameText = MutableLiveData<String>("")
    val classText = MutableLiveData<String>("")

    val isIdCheck = MutableLiveData<Boolean>()
    val isSignUp = MutableLiveData<Boolean>()

    val duplicateBtn = SingleLiveEvent<Unit>()
    val signUpBtn = SingleLiveEvent<Unit>()

    fun duplicateID() {

    }

    fun signUp() {

    }

    fun duplicateClick() = duplicateBtn.call()
    fun signUpClick() = signUpBtn.call()
    

}