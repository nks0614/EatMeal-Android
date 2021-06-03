package com.project.eatmeal.di

import com.project.eatmeal.ui.meal.MealViewModel
import com.project.eatmeal.ui.member.InfoViewModel
import com.project.eatmeal.ui.menu.MenuViewModel
import com.project.eatmeal.ui.menu.frequency.FrequencyViewModel
import com.project.eatmeal.ui.menu.search.SearchViewModel
import com.project.eatmeal.ui.menu.star.StarViewModel
import com.project.eatmeal.ui.today.TodayMealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MealViewModel(get()) }
    viewModel { InfoViewModel() }
    viewModel { FrequencyViewModel(get(), get()) }
    viewModel { StarViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get())}
    viewModel { TodayMealViewModel(get()) }
    viewModel { MenuViewModel() }
}