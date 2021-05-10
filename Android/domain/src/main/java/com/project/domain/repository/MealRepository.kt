package com.project.domain.repository

import com.project.domain.model.body.IdCheckBody
import com.project.domain.model.body.SignInBody
import com.project.domain.model.body.SignUpBody
import com.project.domain.model.body.StarBody
import com.project.domain.model.response.Meal
import com.project.domain.model.response.Member
import com.project.domain.model.response.Menu
import com.project.domain.model.response.TodayMenu
import io.reactivex.Single

interface MealRepository {
    fun getMeal(date : String) : Single<Meal>
    fun getTodayMeal() : Single<TodayMenu>
    fun getAllMenuPercent( kind : Int, page : Int) : Single<Menu>
    fun getAllMenuStar(kind : Int, page : Int) : Single<Menu>
    fun getSearch(name : String, page : Int) : Single<Menu>
    fun login(signInBody : SignInBody) : Single<Member>
    fun signUp(signUpBody : SignUpBody) : Single<Int>
    fun idCheck(idCheckBody: IdCheckBody) : Single<Int>
    fun giveStar(starBody: StarBody) : Single<Int>
}