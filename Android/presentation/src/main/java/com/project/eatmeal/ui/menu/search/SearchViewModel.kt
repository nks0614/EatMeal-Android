package com.project.eatmeal.ui.menu.search

import androidx.lifecycle.MutableLiveData
import com.project.domain.model.response.Food
import com.project.domain.usecase.GetSearchUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.ui.item.menu.MenuItemNavigator
import com.project.eatmeal.widget.toMenuList

class SearchViewModel(
    private val getSearchUseCase: GetSearchUseCase
) : BaseViewModel(), MenuItemNavigator {

    val nameText = MutableLiveData<String>()
    val menuList = MutableLiveData<ArrayList<BindingItem>>()
    var page = 0;

    val isLoading = MutableLiveData<Boolean>(true)
    val hasError = MutableLiveData<Boolean>(false)
    val isFind = MutableLiveData<Boolean>(false)

    fun getSearchMenu() {
        addDisposable(getSearchUseCase.execute(nameText.value!!)
            .subscribe({
                it.foods.add(Food(""))
                menuList.value = ArrayList(it.foods.toMenuList(this))
            },{
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
                isFind.value = true
            }))
    }

    override fun onClickItem(food: Food) {

    }
}