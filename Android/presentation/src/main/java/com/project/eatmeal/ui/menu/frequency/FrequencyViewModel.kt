package com.project.eatmeal.ui.menu.frequency

import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetAllMenuPercentUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.widget.Event
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.ui.item.menu.MenuItemNavigator
import com.project.eatmeal.widget.toMenuList

class FrequencyViewModel(
    private val getAllMenuPercentUseCase: GetAllMenuPercentUseCase,
    val giveStarUseCase: GiveStarUseCase
) : BaseViewModel(), MenuItemNavigator {


    var page = 0
    val menuList = MutableLiveData<ArrayList<BindingItem>>()

    val onErrorEvent = MutableLiveData<Event<String>>()
    val itemFood = MutableLiveData<Food>()

    val isFind = MutableLiveData<Boolean>(false)

    fun getMenuFrequency() {
        addDisposable(getAllMenuPercentUseCase.execute()
            .subscribe({
                it.foods.add(Food(""))
                menuList.value = ArrayList(it.foods.toMenuList(this))
                CashingData.menuData[CashingData.MENU_FREQUENCY_LIST] = menuList.value!!

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
                CashingData.menuData[CashingData.MENU_FREQUENCY_LIST] = list!!
            },{
                isFind.value = true
                val list = menuList.value
                list?.removeLast()
                menuList.value = list
            }))
    }

    override fun onClickItem(food: Food) {
        itemFood.value = food
    }



}