package com.project.domain.usecase

import com.project.domain.model.response.Menu
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetAllMenuPercentUseCase (
    private val repository : MealRepository
){
    fun execute(page : Int, kind : Int) : Single<Menu> =
        repository.getAllMenuPercent(page, kind)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}