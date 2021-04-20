package com.project.eatmeal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.Meal
import com.project.eatmeal.data.response.TodayMenu
import com.project.eatmeal.network.MealAPI
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import com.project.simplecode.spDateFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

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
                            Log.e("LOG TEST", "${response.code()}")
                            with(response.body()){
                                CashingData.mealData.put(CashingData.MEAL_BREAKFAST, replaceText(this?.data?.breakfast!!))
                                CashingData.mealData.put(CashingData.MEAL_LUNCH, replaceText(this?.data?.lunch!!))
                                CashingData.mealData.put(CashingData.MEAL_DINNER, replaceText(this?.data?.dinner!!))
                                isGetMeal.value = !isGetMeal.value!!
                            }
                        } else {
                            Log.e("LOG TEST", "${response.code()}")
                            val errMsg = "존재하지 않습니다."
                            CashingData.mealData.put(CashingData.MEAL_BREAKFAST, errMsg)
                            CashingData.mealData.put(CashingData.MEAL_LUNCH, errMsg)
                            CashingData.mealData.put(CashingData.MEAL_DINNER, errMsg)
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