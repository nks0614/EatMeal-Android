package com.project.eatmeal.ui.today

import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetMealUseCase
import com.project.domain.usecase.GetTodayMealUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.widget.Event
import com.project.eatmeal.widget.SingleLiveEvent
import com.project.eatmeal.widget.toTodayMealList
import com.project.simplecode.spDateFormat


class TodayMealViewModel(
    private val getTodayMealUseCase: GetTodayMealUseCase
) : BaseViewModel() {

    val today = MutableLiveData<String>(spDateFormat("YYYY년 MM월 dd일", 0))

    val list = MutableLiveData<ArrayList<BindingItem>>()

    val breakfastList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()
    val lunchList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()
    val dinnerList : MutableLiveData<ArrayList<BindingItem>> = MutableLiveData()

    val onErrorEvent = MutableLiveData<Event<String>>()

    val breakfastClick = SingleLiveEvent<Unit>()
    val lunchClick = SingleLiveEvent<Unit>()
    val dinnerClick = SingleLiveEvent<Unit>()

    val hasMeal = MutableLiveData<Boolean>(false)
    val getFinish = SingleLiveEvent<Unit>()

    fun getTodayMeal(){
        addDisposable(getTodayMealUseCase.execute()
            .subscribe({
                val breakfast = ArrayList(it.breakfast.toTodayMealList())
                val lunch = ArrayList(it.lunch.toTodayMealList())
                val dinner = ArrayList(it.dinner.toTodayMealList())
                breakfastList.value = breakfast
                lunchList.value = lunch
                dinnerList.value = dinner
                with(CashingData) {
                    todayMealData[TODAY_MEAL_BREAKFAST_LIST] = breakfast
                    todayMealData[TODAY_MEAL_LUNCH_LIST] = lunch
                    todayMealData[TODAY_MEAL_DINNER_LIST] = dinner
                }
                getFinish.call()
            }, {
                onErrorEvent.value = Event(it.message.toString())
                getFinish.call()
            }))
    }

    fun breakfastCall() = breakfastClick.call()
    fun lunchCall() = lunchClick.call()
    fun dinnerCall() = dinnerClick.call()


}