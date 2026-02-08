package com.example.networkexperience.Screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
    val state by postVM.state.collectAsState()

    LaunchedEffect(id) {
        postVM.getPostsId(id)
    }


    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.saveSuccess) {
        when (state.saveSuccess) {
            true -> snackbarHostState.showSnackbar("Успешно сохранено!")
            false -> snackbarHostState.showSnackbar("Ошибка сохранения!")
            null -> {}
        }
        postVM.clearSaveResult() // чтобы не повторялось
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
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

            else -> {
                state.post?.let { post ->
                    PostInfo(postFromUser = post, postVM = postVM)
                }
            }


        }
    }
}



@Composable
fun PostInfo(postFromUser:Post, vm: PostChange =viewModel(), postVM: UserViewModel) {

    val post by vm.state.collectAsState()

    // инициализируем editable пост один раз
    LaunchedEffect(postFromUser) {
        vm.setPost(postFromUser)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )  {
        TextField(
            value = post.userId.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(userId = it.toIntOrNull() ?: 0)) },
            label = { Text("User ID") }
        )
        TextField(
            modifier = Modifier.size(360.dp, 50.dp),
            value = post.id.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(id = it.toIntOrNull() ?: 0)) },
            label = { Text("id") })
        TextField(
            modifier = Modifier.size(360.dp, 100.dp),
            value = post.title.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(title = it)) },
            label = { Text("Title") })
        TextField(
            modifier = Modifier.size(360.dp, 50.dp),
            value = post.body.toString(),
            onValueChange = { it -> vm.OnValueChange(post.copy(body = it)) },
            label = { Text("Body") })
        Button(onClick = { postVM.postPosts(vm.state.value) }) {
            Text(
                text = "Save",
                fontSize = 20.sp
            )
        }
    }
}



