package com.project.eatmeal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.eatmeal.base.BaseViewModel
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.Menu
import com.project.eatmeal.network.NetworkClient
import com.project.eatmeal.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : BaseViewModel() {

    val searchText = MutableLiveData<String>()
    var kindNum = 0
    var sortNum = -1
    var page = 0

    val menuList: MutableLiveData<ArrayList<Food>> = MutableLiveData(ArrayList<Food>())
    val cancelClick = SingleLiveEvent<Unit>()

    val isGetMenuList = MutableLiveData(false)

    fun getMenuList() {

    }


    fun cancelCall() = cancelClick.call()

}