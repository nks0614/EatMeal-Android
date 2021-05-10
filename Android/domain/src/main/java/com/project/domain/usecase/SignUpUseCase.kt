package com.project.domain.usecase

import com.project.domain.model.body.SignUpBody
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpUseCase (
    private val repository : MealRepository
){
    fun execute(body : SignUpBody) : Single<Int> =
        repository.signUp(body)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}