package com.project.domain.repository

import com.project.domain.model.body.StarBody
import com.project.domain.model.response.Meal
import com.project.domain.model.response.Menu
import com.project.domain.model.response.TodayMenu
import io.reactivex.Single

interface MealRepository {
    fun getMeal(date : String) : Single<Meal>
    fun getTodayMeal() : Single<TodayMenu>
    fun getAllMenuPercent( page : Int, kind : Int) : Single<Menu>
    fun getAllMenuStar(page : Int, kind : Int) : Single<Menu>
    fun getSearch(name : String, page : Int) : Single<Menu>
    fun giveStar(starBody: StarBody) : Single<Int>
}