package com.project.eatmeal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.TodayMenu
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayMealViewModel : BaseViewModel() {

    val breakfastList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()
    val lunchList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()
    val dinnerList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()

    val breakfastVisible : MutableLiveData<Boolean> = MutableLiveData(false)
    val lunchVisible : MutableLiveData<Boolean> = MutableLiveData(false)
    val dinnerVisible : MutableLiveData<Boolean> = MutableLiveData(false)

    val breakfastClick = SingleLiveEvent<Unit>()
    val lunchClick = SingleLiveEvent<Unit>()
    val dinnerClick = SingleLiveEvent<Unit>()

    val isGetTodayMeal : MutableLiveData<Boolean> = MutableLiveData(false)

    fun getTodayMeal(){
        NetworkClient.API.today().enqueue(object : Callback<MResponse<TodayMenu>> {
            override fun onResponse(
                    call: Call<MResponse<TodayMenu>>,
                    response: Response<MResponse<TodayMenu>>
            ) {
                if(response.code() == 200){
                    Log.e("LOG TEST", "${response.code()}")
                    with(response.body()){
                        this?.data?.breakfast?.let { breakfastList.value = it }
                        this?.data?.lunch?.let { lunchList.value = it }
                        this?.data?.dinner?.let { dinnerList.value = it }
                    }
                } else {
                    Log.e("LOG TEST", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<MResponse<TodayMenu>>, t: Throwable) {
                Log.e("LOG TEST", "${t.message}")
            }
        })
    }

    fun breakfastViewCall() = breakfastClick.call()
    fun lunchViewCall() = lunchClick.call()
    fun dinnerViewCall() = dinnerClick.call()
}