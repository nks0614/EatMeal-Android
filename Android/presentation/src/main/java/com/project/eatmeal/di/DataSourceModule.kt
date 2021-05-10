package com.project.eatmeal.di

import com.project.data.datasource.MealDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { MealDataSource(get()) }
}