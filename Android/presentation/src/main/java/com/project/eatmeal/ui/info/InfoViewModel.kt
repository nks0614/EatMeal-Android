package com.project.eatmeal.ui.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel

class InfoViewModel : BaseViewModel() {

    val answer1 = MutableLiveData<Boolean>(false)
    val answer2 = MutableLiveData<Boolean>(false)
    val answer3 = MutableLiveData<Boolean>(false)
    val answer4 = MutableLiveData<Boolean>(false)

    fun clickQ1() {
        answer1.value = !answer1.value!!
    }

    fun clickQ2() {
        answer2.value = !answer2.value!!
    }

    fun clickQ3() {
        answer3.value = !answer3.value!!
    }

    fun clickQ4() {
        answer4.value = !answer4.value!!
    }

}