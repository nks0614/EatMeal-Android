package com.project.data.repository

import com.project.data.datasource.MealDataSource
import com.project.domain.model.body.IdCheckBody
import com.project.domain.model.body.SignInBody
import com.project.domain.model.body.SignUpBody
import com.project.domain.model.body.StarBody
import com.project.domain.model.response.Meal
import com.project.domain.model.response.Member
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

    override fun login(signInBody: SignInBody): Single<Member> {
        return dataSource.login(signInBody)
    }

    override fun signUp(signUpBody: SignUpBody): Single<Int> {
        return dataSource.signUp(signUpBody)
    }

    override fun idCheck(idCheckBody: IdCheckBody): Single<Int> {
        return dataSource.idCheck(idCheckBody)
    }

    override fun giveStar(starBody: StarBody): Single<Int> {
        return dataSource.giveStar(starBody)
    }
}