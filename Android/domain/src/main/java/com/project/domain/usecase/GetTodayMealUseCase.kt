package com.project.domain.usecase

import com.project.domain.model.response.TodayMenu
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetTodayMealUseCase (
    private val repository : MealRepository
){
    fun execute() : Single<TodayMenu> =
        repository.getTodayMeal()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}