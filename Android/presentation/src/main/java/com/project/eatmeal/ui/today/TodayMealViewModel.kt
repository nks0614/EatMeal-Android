package com.project.eatmeal.ui.today

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayMealViewModel : BaseViewModel() {

    val breakfastList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()
    val lunchList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()
    val dinnerList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()

    fun getTodayMeal(){

    }

}