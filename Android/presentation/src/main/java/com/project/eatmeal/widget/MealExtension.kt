package com.project.eatmeal.widget

import com.project.domain.model.response.Food
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.ui.item.menu.MenuItemNavigator
import com.project.eatmeal.ui.item.menu.MenuItemViewModel
import com.project.eatmeal.ui.item.todaymeal.TodayMealItemViewModel

fun ArrayList<Food>.toTodayMealList() = map { it.toViewModel() }

fun ArrayList<Food>.toMenuList(navigator : MenuItemNavigator) = map { it.toViewModel(navigator) }

fun Food.toViewModel() = TodayMealItemViewModel(this).toRecyclerItem()

fun Food.toViewModel(navigator: MenuItemNavigator) =
    if(this.name == "") MenuItemViewModel(this).toLoadingItem(navigator)
    else MenuItemViewModel(this).toMenuItem(navigator)


fun TodayMealItemViewModel.toRecyclerItem() =
    BindingItem(
        viewModel = this,
        navigator = "",
        layoutId = R.layout.item_today_meal
    )

fun MenuItemViewModel.toMenuItem(navigator: MenuItemNavigator) =
    BindingItem(
        viewModel = this,
        navigator = navigator,
        layoutId = R.layout.item_menu
    )



fun MenuItemViewModel.toLoadingItem(navigator: MenuItemNavigator) =
    BindingItem(
        viewModel = this,
        navigator = navigator,
        layoutId = R.layout.item_loading
    )

