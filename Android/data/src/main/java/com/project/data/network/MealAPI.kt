package com.project.data.network

import com.project.domain.model.body.StarBody
import com.project.domain.model.response.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealAPI {

    @POST("member/star") //별점
    fun giveStar(
        @Body star : StarBody
    ) : Single<Response<MResponseNoData>>

    @GET("meal") //날짜별 급식
    fun meal(
        @Query("date") date : String
    ) : Single<Response<MResponse<Meal>>>

    @GET("menu") //메뉴
    fun menu(
        @Query("page") page : Int,
        @Query("kind") kind : Int
    ) : Single<Response<MResponse<Menu>>>

    @GET("menu/search") //메뉴검색
    fun search(
        @Query("name") name : String,
        @Query("page") page : Int,
    ) : Single<Response<MResponse<Menu>>>

    @GET("menu/today") //오늘 메뉴
    fun today() : Single<Response<MResponse<TodayMenu>>>


}