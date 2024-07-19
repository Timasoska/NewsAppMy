package com.example.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.Constant
import com.example.newsapp.api.NetworkResponse
import com.example.newsapp.api.NewsModel
import com.example.newsapp.api.RetrofitInstance
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsApi = RetrofitInstance.newsApi// Получаем экземпляр API из RetrofitInstance
    private val _newsResult = MutableLiveData<NetworkResponse<NewsModel>>()// хранит данные, изменяемые во время выполнения программы
    val newsResult : LiveData<NetworkResponse<NewsModel>> = _newsResult // наблюдается во View или Activity для отображения данных или сообщений об ошибках.

    fun getData(query: String) {
        _newsResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = newsApi.getNews(query, Constant.apiKey)// Выполняем запрос к API с указанным запросом и ключом API
                if (response.isSuccessful) {
                    val newsModel = response.body()
                    if (newsModel != null) {
                        _newsResult.value = NetworkResponse.Success(newsModel)
                        /*Log.i("Response", "Number of articles: ${newsModel.articles.size}")
                        newsModel.articles.forEach { article ->
                            Log.i("Article", article.toString()) // Логируем каждую статью
                        }*/
                    } else {
                        _newsResult.value = NetworkResponse.Error("NewsModel is null")
                        //Log.i("Response", "NewsModel is null")
                    }
                } else {
                    _newsResult.value = NetworkResponse.Error(response.message())
                    //Log.e("Error", response.message())
                }
            } catch (e: Exception) {
                _newsResult.value = NetworkResponse.Error(e.toString())
                //Log.e("Exception", e.toString())
            }
        }
    }
}

