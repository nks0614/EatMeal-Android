package com.project.eatmeal.data.response

data class TodayMenu(
    val breakfast : ArrayList<Food>,
    val lunch : ArrayList<Food>,
    val dinner : ArrayList<Food>,
    val date : String
)
