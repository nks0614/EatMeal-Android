package com.project.domain.usecase

import com.project.domain.model.body.SignInBody
import com.project.domain.model.response.Member
import com.project.domain.repository.MealRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginUseCase (
    private val repository : MealRepository
){
    fun execute(body : SignInBody) : Single<Member> =
        repository.login(body)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}