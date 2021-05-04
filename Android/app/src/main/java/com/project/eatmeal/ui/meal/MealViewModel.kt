package com.project.eatmeal.ui.meal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.Meal
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import com.project.simplecode.spDateFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : BaseViewModel() {

    val breakfast : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")
    val lunch : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")
    val dinner : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")

    var progress : Int = 0
    var date : MutableLiveData<String> = MutableLiveData(spDateFormat("YYYY년 MM월 dd일", 0))

    val previousClick = SingleLiveEvent<Unit>()
    val nextClick = SingleLiveEvent<Unit>()

    val isGetMeal : MutableLiveData<Boolean> = MutableLiveData(false)

    fun getMeal(){
        NetworkClient.API.meal(date = spDateFormat("YYYYMMdd", progress))
                .enqueue(object : Callback<MResponse<Meal>>{
                    override fun onResponse(call: Call<MResponse<Meal>>, response: Response<MResponse<Meal>>) {
                        if(response.code() == 200){
                            with(response.body()){
                                breakfast.value = replaceText(this?.data?.breakfast!!)
                                lunch.value = replaceText(this?.data?.lunch!!)
                                dinner.value = replaceText(this?.data?.dinner!!)
                                isGetMeal.value = !isGetMeal.value!!
                            }
                        } else {
                            val errMsg = "존재하지 않습니다."
                            breakfast.value = errMsg
                            lunch.value = errMsg
                            dinner.value = errMsg
                            isGetMeal.value = !isGetMeal.value!!
                        }
                    }

                    override fun onFailure(call: Call<MResponse<Meal>>, t: Throwable) {
                        Log.e("LOG TEST", "${t.message}")
                    }
                })
    }

    fun replaceText(text : String) : String{
        return text.replace("[0-9]".toRegex(),"").replace(".","")
    }

    fun previousCall() = previousClick.call()
    fun nextCall() = nextClick.call()


}