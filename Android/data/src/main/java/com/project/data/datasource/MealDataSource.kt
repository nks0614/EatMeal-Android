package com.project.data.datasource

import com.project.data.network.MealRemote
import com.project.domain.model.body.IdCheckBody
import com.project.domain.model.body.SignInBody
import com.project.domain.model.body.SignUpBody
import com.project.domain.model.body.StarBody
import com.project.domain.model.response.Meal
import com.project.domain.model.response.Member
import com.project.domain.model.response.Menu
import com.project.domain.model.response.TodayMenu
import io.reactivex.Single

class MealDataSource(
    private val remote : MealRemote
) {
    fun getMeal(date : String) : Single<Meal> =
        remote.getMeal(date)

    fun getTodayMeal() : Single<TodayMenu> =
        remote.getTodayMeal()

    fun getAllMenuPercent(page : Int, kind : Int) : Single<Menu> =
        remote.getMenu(page, kind)

    fun getAllMenuStar(page : Int, kind : Int) : Single<Menu> =
        remote.getMenu(page, kind)

    fun getSearch(name : String, page : Int) : Single<Menu> =
        remote.getSearch(name, page)

    fun login(body : SignInBody) : Single<Member> =
        remote.login(body)

    fun signUp(body : SignUpBody) : Single<Int> =
        remote.signUp(body)

    fun idCheck(body : IdCheckBody) : Single<Int> =
        remote.idCheck(body)

    fun giveStar(body : StarBody) : Single<Int> =
        remote.giveStar(body)

}