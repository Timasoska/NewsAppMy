package com.example.newsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.Constant
import com.example.newsapp.api.RetrofitInstance
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsApi = RetrofitInstance.newsApi

    fun getData(query: String) {
        viewModelScope.launch {
            val response = newsApi.getNews(query, Constant.apiKey)
            if (response.isSuccessful) {
                val newsModel = response.body()
                if (newsModel != null) {
                    Log.i("Response", newsModel.toString())
                }
            } else {
                Log.e("Error",response.message())
            }
        }
    }
}
