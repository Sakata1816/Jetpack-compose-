package com.example.networkexperience

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TableRow
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.tooling.ComposeToolingApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.networkexperience.PostsRow
import com.example.networkexperience.data.Comment
import com.example.networkexperience.data.Post
import com.example.networkexperience.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier= Modifier.fillMaxSize()){
                Column(modifier = Modifier.padding(it)
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    PostsScreen()
                    CommentsScreen()
                }

            }
        }
    }

        }


@Composable
fun PostsScreen(vm: UserViewModel= hiltViewModel()){
    val state by vm.state.collectAsState()
    when{
        state.isLoading ->  Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        state.error != null -> Text(text = "Error: ${state.error}",
            modifier = Modifier.size(200.dp))
        else-> Column(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.5f)) {
            TitlePosts()
            LazyColumn() {
                items(state.posts){i->
                    PostsRow(posts = i)
                    Divider()
                }
            }
        }

    }
}

@Composable
fun CommentsScreen(vm: UserViewModel= hiltViewModel()) {
    val state by vm.comState.collectAsState()
    when{
        state.isLoading->Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        state.error != null -> Text(text = "Error: ${state.error}",
            modifier = Modifier.size(200.dp))
        else-> Column(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.5f)) {
            TitleComments()


            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)) {
                IconButton(onClick = {vm.prevPage()}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Влево"
                    )
                }
                IconButton(onClick = {vm.nextPage()}) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Стрелка вправо"
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)) {
                items(state.comments) { currentComment ->
                    CommentsRow(comment = currentComment)
                    Divider(color = LightGray, thickness = 0.5.dp)
                }
            }        }

    }
}

@Composable
fun TitlePosts(){
    Row(modifier = Modifier.fillMaxWidth()
        .background(color =LightGray)){
        Text(text = "UserId",
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
        Text(text = "Id",
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
        Text(text = "Title",
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
        Text(text = "Body",
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun TitleComments(){
    Row (modifier = Modifier.fillMaxWidth()
        .background(color = LightGray)){
        Text(text = "postId",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f))
        Text(text = "id",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f))
        Text(text = "name",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f))
        Text(text = "email",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f))
        Text(text = "body",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f))
    }
}



@Composable
fun PostsRow(posts: Post){
    Row (modifier = Modifier.fillMaxWidth()){
        Text(text = posts.userId.toString(),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
        Text(text=posts.id.toString(),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
        Text(text=posts.title.toString(),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
        Text(text=posts.body.toString(),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun CommentsRow(comment: Comment){

        Row (modifier = Modifier.fillMaxWidth()
            .border(1.dp, DarkGray)){
            Text(text = comment.postId.toString(),
                fontSize = 24.sp,
                modifier = Modifier.weight(1f))
            Text(text=comment.id.toString(),
                fontSize = 24.sp,
                modifier = Modifier.weight(1f))
            Text(text=comment.name.toString(),
                fontSize = 24.sp,
                modifier = Modifier.weight(1f))
            Text(text=comment.email.toString(),
                fontSize = 24.sp,
                modifier = Modifier.weight(1f))
            Text(text=comment.body.toString(),
                fontSize = 24.sp,
                modifier = Modifier.weight(1f))
        }



}