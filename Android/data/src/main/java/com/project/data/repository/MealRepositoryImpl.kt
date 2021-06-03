package com.project.data.repository

import com.project.data.datasource.MealDataSource
import com.project.domain.model.body.StarBody
import com.project.domain.model.response.Meal
import com.project.domain.model.response.Menu
import com.project.domain.model.response.TodayMenu
import com.project.domain.repository.MealRepository
import io.reactivex.Single

class MealRepositoryImpl(
    private val dataSource : MealDataSource
) : MealRepository {

    override fun getMeal(date: String): Single<Meal> {
        return dataSource.getMeal(date)
    }

    override fun getTodayMeal(): Single<TodayMenu> {
        return dataSource.getTodayMeal()
    }

    override fun getAllMenuPercent(page: Int, kind: Int): Single<Menu> {
        return dataSource.getAllMenuPercent(page, kind)
    }

    override fun getAllMenuStar(page: Int, kind: Int): Single<Menu> {
        return dataSource.getAllMenuStar(page, kind)
    }

    override fun getSearch(name: String, page: Int): Single<Menu> {
        return dataSource.getSearch(name, page)
    }

    override fun giveStar(starBody: StarBody): Single<Int> {
        return dataSource.giveStar(starBody)
    }
}