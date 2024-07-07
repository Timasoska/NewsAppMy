package com.example.newsapp.api

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)