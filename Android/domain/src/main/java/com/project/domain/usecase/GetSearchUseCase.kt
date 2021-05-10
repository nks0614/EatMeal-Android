package com.project.domain.usecase

import com.project.domain.model.response.Menu
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetSearchUseCase (
    private val repository : MealRepository
){
    fun execute(name : String, page : Int) : Single<Menu> =
        repository.getSearch(name, page)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}