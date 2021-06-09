package com.project.eatmeal.ui.menu.star

import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetAllMenuStarUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.widget.Event
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.ui.item.menu.MenuItemNavigator
import com.project.eatmeal.widget.toMenuList

class StarViewModel(
    private val getAllMenuStarUseCase: GetAllMenuStarUseCase,
    val giveStarUseCase: GiveStarUseCase
) : BaseViewModel(), MenuItemNavigator {

    var page = 0
    val menuList = MutableLiveData<ArrayList<BindingItem>>()

    val onErrorEvent = MutableLiveData<Event<String>>()
    val itemFood = MutableLiveData<Food>()

    fun getMenuStar() {
        addDisposable(getAllMenuStarUseCase.execute()
            .subscribe({
                it.foods.add(Food(""))
                menuList.value = ArrayList(it.foods.toMenuList(this))
                CashingData.menuData[CashingData.MENU_STAR_LIST] = menuList.value!!
            },{
                onErrorEvent.value = Event(it.message.toString())
            }))
    }

    fun addMenuStar() {
        addDisposable(getAllMenuStarUseCase.execute(page)
            .subscribe({
                menuList.value?.removeAt(menuList.value!!.size - 1)
                it.foods.add(Food(""))
                val list = menuList.value
                list?.addAll(ArrayList(it.foods.toMenuList(this)))
                menuList.value = list
                CashingData.menuData[CashingData.MENU_STAR_LIST] = list!!
            },{
                onErrorEvent.value = Event(it.message.toString())
            }))
    }

    override fun onClickItem(food: Food) {
        itemFood.value = food
    }
}