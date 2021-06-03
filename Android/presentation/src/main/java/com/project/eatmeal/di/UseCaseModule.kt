package com.project.eatmeal.di

import com.project.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAllMenuPercentUseCase(get()) }
    single { GetAllMenuStarUseCase(get()) }
    single { GetMealUseCase(get()) }
    single { GetSearchUseCase(get()) }
    single { GetTodayMealUseCase(get()) }
    single { GiveStarUseCase(get()) }
}