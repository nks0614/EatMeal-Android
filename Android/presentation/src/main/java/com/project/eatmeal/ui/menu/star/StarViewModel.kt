package com.project.eatmeal.ui.menu.star

import com.project.domain.model.response.Food
import com.project.domain.usecase.GetAllMenuStarUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.ui.item.menu.MenuItemNavigator

class StarViewModel(
    private val getAllMenuStarUseCase: GetAllMenuStarUseCase,
    private val giveStarUseCase: GiveStarUseCase
) : BaseViewModel(), MenuItemNavigator {

    override fun onClickItem(food: Food) {

    }
}