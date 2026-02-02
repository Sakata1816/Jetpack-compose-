package com.example.networkexperience.viewModel

import androidx.lifecycle.ViewModel
import com.example.networkexperience.Repositories.UserRepository
import com.example.networkexperience.data.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PostChange @Inject constructor(private val PostRepository: UserRepository) : ViewModel() {

    private val _state= MutableStateFlow(Post())
    val state=_state.asStateFlow()

    fun OnValueChange(post: Post){
        _state.update { it.copy(userId = post.userId,
            id = post.id,
            title = post.title,
            body = post.body) }

    }

}