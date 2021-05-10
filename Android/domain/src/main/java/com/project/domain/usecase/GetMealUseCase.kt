package com.project.domain.usecase

import com.project.domain.model.response.Meal
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetMealUseCase(
    private val repository : MealRepository
){
    fun execute(date : String) : Single<Meal> =
        repository.getMeal(date)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}