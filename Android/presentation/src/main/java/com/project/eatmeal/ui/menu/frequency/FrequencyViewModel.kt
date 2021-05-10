package com.project.eatmeal.ui.menu.frequency

import com.project.domain.usecase.GetAllMenuPercentUseCase
import com.project.domain.usecase.GiveStarUseCase
import com.project.eatmeal.base.BaseViewModel

class FrequencyViewModel(
    private val getAllMenuPercentUseCase: GetAllMenuPercentUseCase,
    private val giveStarUseCase: GiveStarUseCase
) : BaseViewModel() {
}