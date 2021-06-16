package com.project.eatmeal.ui.item.menu

import com.project.domain.model.response.Food

class MenuItemViewModel(val item : Food) {
    fun getStar() : String = String.format("%.1f", item.star)
}