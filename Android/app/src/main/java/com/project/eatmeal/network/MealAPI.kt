package com.project.eatmeal.network

import com.project.eatmeal.data.body.*
import com.project.eatmeal.data.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealAPI {

    @POST("member/signin")
    fun login(
            @Body signIn : SignInBody
    ) : Call<MResponse<Member>>

    @POST("member/signup")
    fun signUp(
        @Body signUp : Member
    ) : Call<MResponseNoData>

    @POST("member/idcheck")
    fun idCheck(
        @Body id : String
    ) : Call<MResponseNoData>

    @POST("member/star")
    fun star(
        @Body star : StarBody
    ) : Call<MResponseNoData>

    @GET("meal")
    fun meal(
        @Query("date") date : String
    ) : Call<MResponse<Meal>>

    @GET("menu")
    fun menu(
        @Query("page") page : Int,
        @Query("sort") sort : Int
    ) : Call<MResponse<Menu>>

    @GET("menu/search")
    fun search(
        @Query("name") name : String
    ) : Call<MResponse<SearchMenu>>

    @GET("menu/today")
    fun today() : Call<MResponse<TodayMenu>>


}