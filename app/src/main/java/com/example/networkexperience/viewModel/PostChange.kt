package com.example.networkexperience.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkexperience.Repositories.UserRepository
import com.example.networkexperience.data.Post
import com.example.networkexperience.data.UserUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostChange() : ViewModel() {

    private val _state= MutableStateFlow(Post())
    val state=_state.asStateFlow()

    fun setPost(post: Post) {
        _state.value = post  // инициализация поста
    }

    fun OnValueChange(post: Post) {
        _state.update { it.copy(userId = post.userId,
            id = post.id,
            title = post.title,
            body = post.body) }
    }


   /* fun PostUser(){
        viewModelScope.launch {
            _state2.update { it.copy(isLoading = true, error = null) }
            try {
    val post =_state.value
    val response = PostRepository.postPost(post)
if(response.isSuccessful){

}
            }

        }
    }*/

}