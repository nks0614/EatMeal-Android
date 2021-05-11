package com.project.eatmeal.ui.menu.search

import com.project.domain.model.response.Food
import com.project.domain.usecase.GetSearchUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.ui.item.menu.MenuItemNavigator

class SearchViewModel(
    private val getSearchUseCase: GetSearchUseCase
) : BaseViewModel(), MenuItemNavigator {

    override fun onClickItem(food: Food) {

    }
}