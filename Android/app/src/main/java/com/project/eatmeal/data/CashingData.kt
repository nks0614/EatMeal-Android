package com.project.eatmeal.data

object CashingData {
    const val TODAY_MEAL_BREAKFAST_LIST = 0
    const val TODAY_MEAL_LUNCH_LIST = 1
    const val TODAY_MEAL_DINNER_LIST = 2
    const val MEAL_BREAKFAST = 3
    const val MEAL_LUNCH = 4
    const val MEAL_DINNER = 5
//    const val MENU_LIST = 6
    const val MENU_KIND_SPINNER = 7
    const val MENU_SORT_SPINNER = 8

    val mealData = hashMapOf<Int, Any>()
    val todayMealData = hashMapOf<Int, Any>()
    val menuData = hashMapOf<Int, Any>()
    val memberData = hashMapOf<Int, Any>()

}