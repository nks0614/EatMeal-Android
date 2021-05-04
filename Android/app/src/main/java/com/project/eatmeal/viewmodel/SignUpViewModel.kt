package com.project.eatmeal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.body.SignUpBody
import com.project.eatmeal.data.body.idCheckBody
import com.project.eatmeal.data.response.MResponseNoData
import com.project.eatmeal.data.response.Member
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : BaseViewModel() {

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
        NetworkClient.API.idCheck(idCheckBody(idText.value!!))
            .enqueue(object : Callback<MResponseNoData> {
                override fun onResponse(call: Call<MResponseNoData>, response: Response<MResponseNoData>) {
                    isIdCheck.value = response.code() == 200
                }
                override fun onFailure(call: Call<MResponseNoData>, t: Throwable) {
                    isIdCheck.value = false
                    Log.d("MYTAG", "Fail : ${t.message}")
                }
            })
    }

    fun signUp() {
        NetworkClient.API.signUp(
            SignUpBody(
                id = idText.value!!,
                pw = pwText.value!!,
                name = nameText.value!!,
                memberClass = classText.value!!
            )
        ).enqueue(object : Callback<MResponseNoData> {
            override fun onResponse(call: Call<MResponseNoData>, response: Response<MResponseNoData>) {
                isSignUp.value = response.code() == 200
            }

            override fun onFailure(call: Call<MResponseNoData>, t: Throwable) {
                isSignUp.value = false
                Log.d("MYTAG", "Fail : ${t.message}")
            }
        })
    }

    fun duplicateClick() = duplicateBtn.call()
    fun signUpClick() = signUpBtn.call()
    

}