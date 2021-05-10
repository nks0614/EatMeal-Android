package com.project.eatmeal.di

import com.project.data.repository.MealRepositoryImpl
import com.project.domain.repository.MealRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MealRepository> { MealRepositoryImpl(get()) }
}