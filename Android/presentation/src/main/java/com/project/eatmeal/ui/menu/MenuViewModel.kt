package com.project.eatmeal.ui.menu

import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel


class MenuViewModel: BaseViewModel() {

    val searchText = MutableLiveData<String>()
    
}