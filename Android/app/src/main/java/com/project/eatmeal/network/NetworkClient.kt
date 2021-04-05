package com.project.eatmeal.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private var instance: Retrofit? = null
    private var API : MealAPI? = null

    private val baseURL : String = "http://15.164.219.30/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private var client = OkHttpClient.Builder().apply {
        addInterceptor (interceptor)
    }.build()

    fun getAPI(): MealAPI {
        if(API == null){
            API = instance?.create(MealAPI::class.java)
        }
        return API!!
    }

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}