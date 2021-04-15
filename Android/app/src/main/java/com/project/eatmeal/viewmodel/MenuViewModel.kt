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

    val menuList : MutableLiveData<ArrayList<Food>> = MutableLiveData()
    val filterClick = SingleLiveEvent<Unit>()
    val cancelClick = SingleLiveEvent<Unit>()

    val isGetMenuList = MutableLiveData<Boolean>(false)

    fun getMenuList(){
        NetworkClient.API.menu(page, sortNum, kindNum)
                .enqueue(object : Callback<MResponse<Menu>>{
                    override fun onResponse(call: Call<MResponse<Menu>>, response: Response<MResponse<Menu>>) {
                        val list = (CashingData.menuData[CashingData.MENU_LIST] as ArrayList<Food>?)
                        when(response.code()){
                            200 -> {
                                if(CashingData.menuData[CashingData.MENU_LIST] == null){
                                    response.body()?.data?.foods?.let {
                                        CashingData.menuData.put(CashingData.MENU_LIST, it)
                                    }
                                } else {
                                    response.body()?.data?.foods?.let {
                                        list?.removeAt(list.lastIndex)
                                        list?.addAll(it)
                                        list?.add(Food("", 0, 0.0, 0, 0, 0, 0.0, ArrayList()))
                                    }
                                }
                                isGetMenuList.value = !isGetMenuList.value!!
                            }
                            404 -> {
                                isGetMenuList.value = !isGetMenuList.value!!
                            }
                            else -> {
                                Log.d("tests", "${response.code()}")
                            }
                        }
                    }
                    override fun onFailure(call: Call<MResponse<Menu>>, t: Throwable) {
                        Log.e("tests", "${t.message}")
                    }
                })
    }

    fun filterCall() = filterClick.call()
    fun cancelCall() = cancelClick.call()

}