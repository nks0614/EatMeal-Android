package com.project.domain.usecase

import com.project.domain.model.body.IdCheckBody
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IdCheckUseCase (
    private val repository : MealRepository
){
    fun execute(body : IdCheckBody) : Single<Int> =
        repository.idCheck(body)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}