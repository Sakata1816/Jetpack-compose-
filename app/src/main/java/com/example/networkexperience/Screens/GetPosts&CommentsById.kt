package com.example.networkexperience.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkexperience.data.Post
import com.example.networkexperience.viewModel.PostChange
import com.example.networkexperience.viewModel.UserViewModel

@Composable
fun GetPost(id:Int) {
    val postVM: UserViewModel = hiltViewModel()

    LaunchedEffect(id) {
        postVM.getPostsId(id)
    }
    val state by postVM.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {


        when {
            state.isLoading -> Box(
                modifier = Modifier.padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            state.error != null -> Text(
                text = "Error: ${state.error}",
                modifier = Modifier.padding(it)
                    .size(200.dp)
            )

            else -> LazyRow(modifier = Modifier.padding(it)) {
                items(state.posts.size) { i ->
                    val post = state.posts[i]
                    PostInfo(post = post)
                    Spacer(modifier = Modifier.size(200.dp))
                }
            }
        }
    }
}



@Composable
fun PostInfo(post: Post,vm: PostChange=viewModel(),postVM: UserViewModel= hiltViewModel()) {
    Column() {
        TextField(
            value = post.userId.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(userId = it.toIntOrNull() ?: 0)) })
        TextField(
            value = post.id.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(id = it.toIntOrNull() ?: 0)) })
        TextField(
            value = post.title.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(title = it)) })
        TextField(
            value = post.body.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(body = it)) })
        Button(onClick = { postVM.postPosts(vm.state.value) }) {
            Text(
                text = "Save",
                fontSize = 20.sp
            )
        }
    }
}



