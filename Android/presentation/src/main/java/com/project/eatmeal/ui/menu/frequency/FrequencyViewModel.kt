package com.project.eatmeal.ui.menu.frequency

import android.app.AlertDialog
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetAllMenuPercentUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.base.Event
import com.project.eatmeal.ui.item.menu.MenuItemNavigator
import com.project.eatmeal.ui.item.menu.MenuItemViewModel
import com.project.eatmeal.widget.SingleLiveEvent
import com.project.eatmeal.widget.toMenuList
import io.reactivex.disposables.Disposable

class FrequencyViewModel(
    private val getAllMenuPercentUseCase: GetAllMenuPercentUseCase,
    private val giveStarUseCase: GiveStarUseCase
) : BaseViewModel(), MenuItemNavigator {

    var page = 0
    val menuList = MutableLiveData<ArrayList<BindingItem>>()

    val onErrorEvent = MutableLiveData<Event<String>>()
    val itemFood = MutableLiveData<Food>()

    fun getMenuFrequency() {
        addDisposable(getAllMenuPercentUseCase.execute()
            .subscribe({
                it.foods.add(Food(""))
                menuList.value = ArrayList(it.foods.toMenuList(this))

            },{
                onErrorEvent.value = Event(it.message.toString())
            }))
    }

    fun addMenuFrequency() {
        addDisposable(getAllMenuPercentUseCase.execute(page)
            .subscribe({
                menuList.value?.removeAt(menuList.value!!.size - 1)
                it.foods.add(Food(""))
                val list = menuList.value
                list?.addAll(ArrayList(it.foods.toMenuList(this)))
                menuList.value = list
            },{
                onErrorEvent.value = Event(it.message.toString())
            }))
    }

    override fun onClickItem(food: Food) {
        itemFood.value = food
    }



}