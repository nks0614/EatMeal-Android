package com.project.eatmeal.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.adapter.TodayMealAdapter
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.TodayMenu
import com.project.eatmeal.network.MealAPI
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MealViewModel : BaseViewModel() {

    val retofit : Retrofit = NetworkClient.getInstance()
    val api : MealAPI = NetworkClient.getAPI()

    var breakfastList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()
    var lunchList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()
    var dinnerList :  MutableLiveData<ArrayList<Food>> =  MutableLiveData()

    var breakfastVisible : MutableLiveData<Boolean> = MutableLiveData(false)
    var lunchVisible : MutableLiveData<Boolean> = MutableLiveData(false)
    var dinnerVisible : MutableLiveData<Boolean> = MutableLiveData(false)

    val breakfastClick = SingleLiveEvent<Unit>()
    val lunchClick = SingleLiveEvent<Unit>()
    val dinnerClick = SingleLiveEvent<Unit>()

    fun getTodayMeal(){
        api.today().enqueue(object : Callback<MResponse<TodayMenu>>{
            override fun onResponse(
                call: Call<MResponse<TodayMenu>>,
                response: Response<MResponse<TodayMenu>>
            ) {
                if(response.code() == 200){
                    Log.d("LOG TEST", "${response.code()}")
                    with(response.body()){
                        breakfastList.value = this?.data?.breakfast
                        lunchList.value = this?.data?.lunch
                        dinnerList.value = this?.data?.dinner
                    }
                } else {
                    Log.d("LOG TEST", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<MResponse<TodayMenu>>, t: Throwable) {
                Log.d("LOG TEST", "${t.message}")
            }
        })
    }

    fun breakfastViewCall() = breakfastClick.call()
    fun lunchViewCall() = lunchClick.call()
    fun dinnerViewCall() = dinnerClick.call()


}