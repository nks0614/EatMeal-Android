package com.project.eatmeal.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    var API : MealAPI

    private val baseURL : String = "http://15.164.219.30/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private var client = OkHttpClient.Builder().apply {
        addInterceptor (interceptor)
    }.build()

    init{
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        API = retrofit.create(MealAPI::class.java)

    }


}