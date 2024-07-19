package com.example.newsapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.Article
import com.example.newsapp.api.NetworkResponse

@Composable
fun NewsPage(viewModel: NewsViewModel){

    var news by remember { mutableStateOf("")}
    val newsResult = viewModel.newsResult.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = news,
                onValueChange ={
                    news = it
                },
                label = {
                    Text(text = "Search news")
                }
            )

            IconButton(onClick = {
                viewModel.getData(news)
            }) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(40.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }

        when(val result = newsResult.value){
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                Text(text = "Number of articles: ${result.data.articles.size}")
                    LazyColumn(modifier = Modifier.fillMaxSize()
                        .padding(5.dp)
                    ) {
                        itemsIndexed(result.data.articles){ index, article ->
                            ArticleItem(article = article)
                        }
                    }
                /*result.data.articles.forEach{ article ->
                    Text(text = article.title)
                }*/
            }
            null ->{ Text(text = "NULL")}
        }

    }
}

@Composable
fun ArticleItem(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = androidx.compose.ui.graphics.Color.LightGray)
            .padding(16.dp)
    ) {
        Text(
            text = article.title,
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        Text(
            text = article.description ?: "No description",
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
        )
    }
}