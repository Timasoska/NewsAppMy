package com.example.newsapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/everything?")
    suspend fun getNews(
        @Query("q") news : String, //Возможно исправить 16.15 на видео
        @Query("apiKey") apiKey : String
    ){

    }
}