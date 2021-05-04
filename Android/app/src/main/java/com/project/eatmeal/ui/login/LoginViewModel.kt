package com.project.eatmeal.ui.login

import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.body.SignInBody
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.Member
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

    val idText : MutableLiveData<String> = MutableLiveData("")
    val pwText : MutableLiveData<String> = MutableLiveData("")
    val checked : MutableLiveData<Boolean> = MutableLiveData(false)

    val signUpBtn = SingleLiveEvent<Unit>()
    val signInBtn = SingleLiveEvent<Unit>()

    val isGetLoginData = MutableLiveData<Boolean>(false)
    var status : Int = 0

    fun login() {
        NetworkClient.API.login(SignInBody(idText.value!!, pwText.value!!))
            .enqueue(object : Callback<MResponse<Member>>{
                override fun onResponse(
                    call: Call<MResponse<Member>>,
                    response: Response<MResponse<Member>>
                ) {
                    when(response.code()){
                        200 -> {
                            response.body()?.data?.id?.let { CashingData.memberData.put(CashingData.MEMBER_ID, it) }
                            response.body()?.data?.memberClass?.let { CashingData.memberData.put(CashingData.MEMBER_CLASS, it) }
                            response.body()?.data?.name?.let { CashingData.memberData.put(CashingData.MEMBER_NAME, it) }
                            response.body()?.data?.starFood?.let { CashingData.memberData.put(CashingData.MEMBER_FOODS, it) }
                        }
                    }
                    status = response.code()
                    isGetLoginData.value = !isGetLoginData.value!!
                }

                override fun onFailure(call: Call<MResponse<Member>>, t: Throwable) {

                }
            })
    }

    fun signUpBtnCall() = signUpBtn.call()
    fun signInBtnCall() = signInBtn.call()


}