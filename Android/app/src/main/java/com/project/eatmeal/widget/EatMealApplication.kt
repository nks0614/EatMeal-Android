package com.project.eatmeal.widget

import android.app.Application
import android.content.Context
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.body.SignInBody
import com.project.eatmeal.data.response.MResponse
import com.project.eatmeal.data.response.Member
import com.project.eatmeal.network.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EatMealApplication : Application() {

    companion object {
        const val SHARED = "user"
        const val ID = "ID"
        const val PW = "PW"
    }

    override fun onCreate() {
        super.onCreate()

        val sharedPref = getSharedPreferences(SHARED, Context.MODE_PRIVATE)
        val id = sharedPref.getString(ID, null) ?: return
        val pw = sharedPref.getString(PW, null) ?: return
        login(id, pw)

    }

    private fun login(id : String, pw : String) {
        NetworkClient.API.login(
            SignInBody(id, pw)
        ).enqueue(object : Callback<MResponse<Member>>{
            override fun onResponse(call: Call<MResponse<Member>>, response: Response<MResponse<Member>>) {
                if(response.code() == 200) {
                    response.body()?.data?.name?.let { CashingData.memberData.put(CashingData.MEMBER_NAME, it) }
                    response.body()?.data?.memberClass?.let { CashingData.memberData.put(CashingData.MEMBER_CLASS, it) }
                    response.body()?.data?.id?.let { CashingData.memberData.put(CashingData.MEMBER_ID, it) }
                    response.body()?.data?.starFood?.let { CashingData.memberData.put(CashingData.MEMBER_FOODS, it) }
                }
            }

            override fun onFailure(call: Call<MResponse<Member>>, t: Throwable) {

            }
        })
    }

}