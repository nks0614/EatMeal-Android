package com.project.eatmeal.network

import com.project.eatmeal.data.body.*
import com.project.eatmeal.data.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealAPI {

    @POST("member/signin") //로그인
    fun login(
            @Body signIn : SignInBody
    ) : Call<MResponse<Member>>

    @POST("member/signup") //회원가입
    fun signUp(
        @Body signUp : Member
    ) : Call<MResponseNoData>

    @POST("member/idcheck") //id 중복체크
    fun idCheck(
        @Body id : String
    ) : Call<MResponseNoData>

    @POST("member/star") //별점
    fun star(
        @Body star : StarBody
    ) : Call<MResponseNoData>

    @GET("meal") //날짜별 급식
    fun meal(
        @Query("date") date : String
    ) : Call<MResponse<Meal>>

    @GET("menu") //메뉴
    fun menu(
        @Query("page") page : Int,
        @Query("sort") sort : Int,
        @Query("kind") kind : Int
    ) : Call<MResponse<Menu>>

    @GET("menu/search") //메뉴검색
    fun search(
        @Query("name") name : String,
        @Query("page") page : Int,
        @Query("sort") sort : Int,
        @Query("kind") kind : Int
    ) : Call<MResponse<Menu>>

    @GET("menu/today") //오늘 메뉴
    fun today() : Call<MResponse<TodayMenu>>


}