package com.project.eatmeal.ui.menu

import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.widget.SingleLiveEvent


class MenuViewModel: BaseViewModel() {

    val searchText = MutableLiveData<String>()
    val searchBtn = SingleLiveEvent<Unit>()

    fun searchClick() = searchBtn.call()
}