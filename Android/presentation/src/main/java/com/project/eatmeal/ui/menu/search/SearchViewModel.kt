package com.project.eatmeal.ui.menu.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetSearchUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.ui.item.menu.MenuItemNavigator
import com.project.eatmeal.widget.toMenuList

class SearchViewModel(
    private val getSearchUseCase: GetSearchUseCase,
    val giveStarUseCase: GiveStarUseCase
) : BaseViewModel(), MenuItemNavigator {

    val nameText = MutableLiveData<String>()
    val menuList = MutableLiveData<ArrayList<BindingItem>>()
    var page = 0;

    val isLoading = MutableLiveData<Boolean>(true)
    val hasError = MutableLiveData<Boolean>(false)
    val isFind = MutableLiveData<Boolean>(false)

    val itemFood = MutableLiveData<Food>()

    fun getSearchMenu() {
        addDisposable(getSearchUseCase.execute(nameText.value!!)
            .subscribe({
                it.foods.add(Food(""))
                menuList.value = ArrayList(it.foods.toMenuList(this))
                isLoading.value = false
            },{
                isLoading.value = false
                hasError.value = true
            }))
    }

    fun addSearchMenu() {
        addDisposable(getSearchUseCase.execute(nameText.value!!, page)
            .subscribe({
                menuList.value?.removeAt(menuList.value!!.size - 1)
                it.foods.add(Food(""))
                val list = menuList.value
                list?.addAll(ArrayList(it.foods.toMenuList(this)))
                menuList.value = list

            },{
                Log.d("MYTAG", "THROW")
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