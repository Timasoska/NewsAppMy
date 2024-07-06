package com.example.newsapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun NewsPage(viewModel: NewsViewModel){

    var news by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
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
                    modifier = Modifier.fillMaxSize()
                        .size(40.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }

        }
    }
}