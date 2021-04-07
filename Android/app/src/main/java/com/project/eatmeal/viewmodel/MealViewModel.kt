package com.project.eatmeal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
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

    val retofit : Retrofit = NetworkClient.getInstance()
    val api : MealAPI = NetworkClient.getAPI()

    var breakfast : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")
    var lunch : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")
    var dinner : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")

    var breakfastList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()
    var lunchList : MutableLiveData<ArrayList<Food>> =  MutableLiveData()
    var dinnerList :  MutableLiveData<ArrayList<Food>> =  MutableLiveData()

    var breakfastVisible : MutableLiveData<Boolean> = MutableLiveData(false)
    var lunchVisible : MutableLiveData<Boolean> = MutableLiveData(false)
    var dinnerVisible : MutableLiveData<Boolean> = MutableLiveData(false)

    val breakfastClick = SingleLiveEvent<Unit>()
    val lunchClick = SingleLiveEvent<Unit>()
    val dinnerClick = SingleLiveEvent<Unit>()

    var progress : Int = 0
    var date : MutableLiveData<String> = MutableLiveData(spDateFormat("YYYY년 MM월 dd일", 0))

    val previousClick = SingleLiveEvent<Unit>()
    val nextClick = SingleLiveEvent<Unit>()

    var isGetTodayMeal : MutableLiveData<Boolean> = MutableLiveData(false)
    var isGetMeal : MutableLiveData<Boolean> = MutableLiveData(false)

    fun getTodayMeal(){
        api.today().enqueue(object : Callback<MResponse<TodayMenu>>{
            override fun onResponse(
                call: Call<MResponse<TodayMenu>>,
                response: Response<MResponse<TodayMenu>>
            ) {
                if(response.code() == 200){
                    Log.e("LOG TEST", "${response.code()}")
                    with(response.body()){
                        breakfastList.value = this?.data?.breakfast
                        lunchList.value = this?.data?.lunch
                        dinnerList.value = this?.data?.dinner

                        isGetTodayMeal.value = !isGetTodayMeal.value!!
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

    fun getMeal(){
        api.meal(date = spDateFormat("YYYYMMdd", progress))
                .enqueue(object : Callback<MResponse<Meal>>{
                    override fun onResponse(call: Call<MResponse<Meal>>, response: Response<MResponse<Meal>>) {
                        if(response.code() == 200){
                            Log.e("LOG TEST", "${response.code()}")
                            with(response.body()){

                                breakfast.value = replaceText(this?.data?.breakfast!!)
                                lunch.value = replaceText(this?.data?.lunch!!)
                                dinner.value = replaceText(this?.data?.dinner!!)

                                isGetMeal.value = !isGetMeal.value!!
                            }
                        } else {
                            Log.e("LOG TEST", "${response.code()}")
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

    fun breakfastViewCall() = breakfastClick.call()
    fun lunchViewCall() = lunchClick.call()
    fun dinnerViewCall() = dinnerClick.call()

    fun previousCall() = previousClick.call()
    fun nextCall() = nextClick.call()


}