package com.example.newsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.Constant
import com.example.newsapp.api.RetrofitInstance
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsApi = RetrofitInstance.newsApi

    fun getData(news : String){

        viewModelScope.launch {
            val response = newsApi.getNews(Constant.apiKey, news)
            if(response.isSuccessful){
                Log.i("Response :",response.body().toString())
            }else{
                Log.i("Error :",response.message())
            }
        }
    }

}