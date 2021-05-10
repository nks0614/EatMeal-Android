package com.project.domain.usecase

import com.project.domain.model.body.StarBody
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GiveStarUseCase (
    private val repository : MealRepository
){
    fun execute(body : StarBody) : Single<Int> =
        repository.giveStar(body)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}