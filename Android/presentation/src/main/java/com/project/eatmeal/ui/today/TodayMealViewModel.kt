package com.project.eatmeal.ui.today

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetTodayMealUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.base.Event
import com.project.eatmeal.ui.item.todaymeal.TodayMealItemNavigator


class TodayMealViewModel(
    private val getTodayMealUseCase: GetTodayMealUseCase
) : BaseViewModel(), TodayMealItemNavigator {

    val breakfastList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()
    val lunchList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()
    val dinnerList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()

    val isLoading = MutableLiveData(false)
    val onErrorEvent = MutableLiveData<Event<String>>()

    fun getTodayMeal(){
//        addDisposable(getTodayMealUseCase.execute()
//            .subscribe({
//                isLoading.value = true
//                val breakfast = ArrayList(it.breakfast.toTodayMealList(this))
//                val lunch = ArrayList(it.lunch.toTodayMealList((this)))
//                val dinner = ArrayList(it.dinner.toTodayMealList(this))
//                breakfastList.value = breakfast
//                lunchList.value = lunch
//                dinnerList.value = dinner
//                with(CashingData) {
//                    todayMealData[TODAY_MEAL_BREAKFAST_LIST] = breakfast
//                    todayMealData[TODAY_MEAL_LUNCH_LIST] = lunch
//                    todayMealData[TODAY_MEAL_DINNER_LIST] = dinner
//                }
//            }, {
//                onErrorEvent.value = Event(it.message.toString())
//            }))
    }

    override fun onClickItem(food: Food) {

    }

}