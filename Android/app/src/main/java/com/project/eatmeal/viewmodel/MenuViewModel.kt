package com.project.eatmeal.viewmodel

import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel

class MenuViewModel : BaseViewModel() {

    val searchText = MutableLiveData<String>()

}