package com.project.eatmeal.di

import com.project.data.network.MealRemote
import org.koin.dsl.module

val remoteModule = module {
    single { MealRemote(get()) }
}