package com.project.eatmeal.ui.item.todaymeal

import com.project.domain.model.response.Food

class TodayMealItemViewModel(val item : Food) {
    fun getInfoText() : String = "${item.frequency}번, 즉 ${item.percent}%만큼 나왔고, 별점은 ${item.star}점입니다."
}