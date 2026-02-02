package com.example.networkexperience.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkexperience.data.Post
import com.example.networkexperience.viewModel.PostChange

@Composable
fun GetPost(id:Int){
Scaffold(modifier=Modifier.fillMaxSize(),
    ){
    Column (modifier = Modifier.padding(it)){

    }
}
}


@Composable
fun PostInfo(post: Post,vm: PostChange=viewModel()){
    Column {
        TextField(value = post.userId.toString(),
            onValueChange ={it->vm.OnValueChange(post.copy(userId = it.toInt()))})
        TextField(value =post.id.toString(),
            onValueChange ={it->vm.OnValueChange(post.copy(id = it.toInt()))})
        TextField(value = post.title.toString(),
            onValueChange ={it->vm.OnValueChange(post.copy(title = it))})
        TextField(value = post.body.toString(),
            onValueChange ={it->vm.OnValueChange(post.copy(body = it))})
}
}

