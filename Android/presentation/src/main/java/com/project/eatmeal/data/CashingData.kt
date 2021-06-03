package com.project.eatmeal.data

object CashingData {
    const val TODAY_MEAL_BREAKFAST_LIST = 0
    const val TODAY_MEAL_LUNCH_LIST = 1
    const val TODAY_MEAL_DINNER_LIST = 2
    const val MENU_FREQUENCY_LIST = 3
    const val MENU_STAR_LIST = 4

    var MAC_ADDRESS = ""

    val todayMealData = hashMapOf<Int, Any>()
    val menuData = hashMapOf<Int, Any>()

}