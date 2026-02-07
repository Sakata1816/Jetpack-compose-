package com.example.networkexperience.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp






@Composable
fun ScreensColumn (onItemClick:(String)-> Unit){
    Column {
        Screens("Posts&Comments","PostsAndComments",onItemClick )
    }
}


@Composable
fun Screens(Title: String,route: String, onClick: (String) -> Unit){
    Text(text= Title,
        fontSize = 24.sp,
        modifier = Modifier.clickable(onClick={onClick(route)}))
}