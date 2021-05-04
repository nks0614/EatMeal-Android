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
    val isGetMenuList = MutableLiveData<Boolean>(false)

    var listType : Boolean = true

    fun getMenuList(type : Int) {
        NetworkClient.API.menu(page, sortNum, kindNum).enqueue(object : Callback<MResponse<Menu>>{
            override fun onResponse(call: Call<MResponse<Menu>>, response: Response<MResponse<Menu>>) {
                when(response.code()){
                    200 -> {
                        if(type == 0){
                            response.body()?.data?.foods?.let { menuList.value = it }
                            menuList.value?.add(Food("", 0, 0.0, 0, 0, 0, 0.0, ArrayList()))
                            listType = true
                        } else {
                            menuList.value?.removeAt(menuList.value!!.lastIndex)
                            menuList.value?.addAll(response.body()?.data?.foods!!)
                            menuList.value?.add(Food("", 0, 0.0, 0, 0, 0, 0.0, ArrayList()))
                            val list = menuList.value
                            menuList.value = list
                        }

                    }
                    404 -> {
                        if(type == 1) menuList.value!!.removeAt(menuList.value!!.lastIndex)
                        val list = menuList.value
                        menuList.value = list
                    }
                    else -> {
                        Log.d("tests", "${response.code()}")
                    }
                }
                isGetMenuList.value = !isGetMenuList.value!!
            }

            override fun onFailure(call: Call<MResponse<Menu>>, t: Throwable) {
                Log.d("tests", "${t.message}")
                isGetMenuList.value = !isGetMenuList.value!!
            }
        })
    }

    fun getSearchList(type : Int) {
        NetworkClient.API.search(searchText.value!!, page, sortNum, kindNum)
            .enqueue(object : Callback<MResponse<Menu>>{
                override fun onResponse(call: Call<MResponse<Menu>>, response: Response<MResponse<Menu>>) {
                    when(response.code()){
                        200 -> {
                            if(type == 0){
                                response.body()?.data?.foods?.let { menuList.value = it }
                                menuList.value?.add(Food("", 0, 0.0, 0, 0, 0, 0.0, ArrayList()))
                                listType = false
                            } else {
                                menuList.value?.removeAt(menuList.value!!.lastIndex)
                                menuList.value?.addAll(response.body()?.data?.foods!!)
                                menuList.value?.add(Food("", 0, 0.0, 0, 0, 0, 0.0, ArrayList()))
                                val list = menuList.value
                                menuList.value = list
                            }

                        }
                        404 -> {
                            if(type == 1 && menuList.value!!.last().name == "") {
                                menuList.value!!.removeAt(menuList.value!!.lastIndex)
                                val list = menuList.value
                                menuList.value = list
                            }

                        }
                        else -> {
                            Log.d("tests", "${response.code()}")
                        }
                    }
                }

                override fun onFailure(call: Call<MResponse<Menu>>, t: Throwable) {
                    Log.d("tests", "${t.message}")
                }
            })
    }


    fun cancelCall() = cancelClick.call()

}