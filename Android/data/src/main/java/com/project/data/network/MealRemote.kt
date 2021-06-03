package com.project.data.network

import com.project.domain.model.body.StarBody
import com.project.domain.model.response.Meal
import com.project.domain.model.response.Menu
import com.project.domain.model.response.TodayMenu
import io.reactivex.Single

class MealRemote (
    private val api : MealAPI
){
    fun getMeal(date : String) : Single<Meal> =
        api.meal(date).map { it.body()?.data }

    fun getTodayMeal() : Single<TodayMenu> =
        api.today().map { it.body()?.data }

    fun getMenu(page : Int, kind : Int) : Single<Menu> =
        api.menu(page, kind).map { it.body()?.data }

    fun getSearch(name : String, page : Int) : Single<Menu> =
        api.search(name, page).map { it.body()?.data }

    fun giveStar(body : StarBody) : Single<Int> =
        api.giveStar(body).map { it.body()?.status }
}