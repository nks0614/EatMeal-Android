package com.project.eatmeal.ui.meal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.domain.usecase.GetMealUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.Event
import com.project.eatmeal.widget.SingleLiveEvent
import com.project.simplecode.spDateFormat

class MealViewModel(
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {

    val breakfast : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")
    val lunch : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")
    val dinner : MutableLiveData<String> = MutableLiveData("로딩 중입니다.")

    var progress : Int = 0
    var date : MutableLiveData<String> = MutableLiveData(spDateFormat("YYYY년 MM월 dd일", 0))

    val previousClick = SingleLiveEvent<Unit>()
    val nextClick = SingleLiveEvent<Unit>()

    val onErrorEvent = MutableLiveData<Event<String>>()

    fun getMeal(){
        addDisposable(getMealUseCase.execute(spDateFormat("YYYYMMdd", progress))
            .subscribe({
                breakfast.value = replaceText(it.breakfast)
                lunch.value = replaceText(it.lunch)
                dinner.value = replaceText(it.dinner)
            }, {
                breakfast.value = "존재하지 않습니다."
                lunch.value = "존재하지 않습니다."
                dinner.value = "존재하지 않습니다."
                onErrorEvent.value = Event(it.message.toString())
            }))
    }

    fun replaceText(text : String) : String{
        return text.replace("[0-9]".toRegex(),"").replace(".","")
    }

    fun previousCall() = previousClick.call()
    fun nextCall() = nextClick.call()


}