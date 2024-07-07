package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseUrl = " https://newsapi.org"

    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val newsApi : NewsApi = getInstance().create(NewsApi::class.java) //позволяет использовать интерфейс NewsApi для выполнения HTTP-запросов.

}