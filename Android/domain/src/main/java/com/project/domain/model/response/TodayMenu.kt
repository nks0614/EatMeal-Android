package com.project.domain.model.response

import com.project.domain.model.response.Food

data class TodayMenu(
    val breakfast : ArrayList<Food>,
    val lunch : ArrayList<Food>,
    val dinner : ArrayList<Food>,
    val date : String
)
