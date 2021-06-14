package com.project.eatmeal.ui.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.widget.SingleLiveEvent

class InfoViewModel : BaseViewModel() {

    val answer1 = MutableLiveData<Boolean>(false)
    val answer2 = MutableLiveData<Boolean>(false)
    val answer3 = MutableLiveData<Boolean>(false)
    val answer4 = MutableLiveData<Boolean>(false)
    val app2 = MutableLiveData<Boolean>(false)

    val app1 = SingleLiveEvent<Unit>()
    val app3 = SingleLiveEvent<Unit>()

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

    fun clickA2() {
        app2.value = !app2.value!!
    }

    fun app1Click() = app1.call()
    fun app3Click() = app3.call()

}