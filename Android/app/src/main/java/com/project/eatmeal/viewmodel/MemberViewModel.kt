package com.project.eatmeal.viewmodel

import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.widget.SingleLiveEvent

class MemberViewModel : BaseViewModel() {

    val memberName : MutableLiveData<String> = MutableLiveData("로그인이 필요합니다.")
    val memberClass : MutableLiveData<String> = MutableLiveData()

    val loginClick = SingleLiveEvent<Unit>()
    val logoutClick = SingleLiveEvent<Unit>()

    fun logoutCall() = logoutClick.call()
    fun loginCall() = loginClick.call()
}