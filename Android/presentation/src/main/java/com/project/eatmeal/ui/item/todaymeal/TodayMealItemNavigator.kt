package com.project.eatmeal.ui.item.todaymeal

import com.project.domain.model.response.Food

interface TodayMealItemNavigator {
    fun onClickItem(food : Food)
}