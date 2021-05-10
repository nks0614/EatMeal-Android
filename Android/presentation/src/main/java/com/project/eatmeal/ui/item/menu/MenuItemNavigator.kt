package com.project.eatmeal.ui.item.menu

import com.project.domain.model.response.Food

interface MenuItemNavigator {
    fun onClickItem(food : Food)
}