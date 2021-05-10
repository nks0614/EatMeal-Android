package com.project.eatmeal.ui.menu.star

import com.project.domain.usecase.GetAllMenuStarUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel

class StarViewModel(
    private val getAllMenuStarUseCase: GetAllMenuStarUseCase,
    private val giveStarUseCase: GiveStarUseCase
) : BaseViewModel() {
}