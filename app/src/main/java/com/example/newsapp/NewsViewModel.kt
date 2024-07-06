package com.example.newsapp

import android.util.Log
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    fun getData(news : String){
        Log.i("news", news)
    }

}