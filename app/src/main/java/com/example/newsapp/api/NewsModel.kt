package com.example.newsapp.api

data class NewsModel(
    val sources: List<Source>,
    val status: String
)