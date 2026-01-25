package com.example.networkexperience.viewModel

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collection.MutableVector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.networkexperience.Repositories.*
import com.example.networkexperience.api.CommentApi
import com.example.networkexperience.api.UserApi
import com.example.networkexperience.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.jvm.java




@HiltViewModel
class UserViewModel @Inject constructor(private val PostRepository: UserRepository,private val ComRepository: CommentRepository) : ViewModel() {
    var page by mutableStateOf(1)
        private set

    fun nextPage() {
        page += 1
    loadComments()
    }
    fun prevPage() {
        if(page > 0) page -= 1
    loadComments()
    }
    private val _state = MutableStateFlow(UserUIState())
    private val _comState=MutableStateFlow(UserUIState())

    val comState= _comState.asStateFlow()
    val state = _state.asStateFlow()


    init {
        loadUsers()
        loadComments()
    }
    private fun loadUsers() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val posts = PostRepository.getPosts()
                _state.update { it.copy(posts = posts, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }

    }

    private fun loadComments() {
        viewModelScope.launch {
            _comState.update { it.copy(isLoading = true, error = null) }
            try {
                val comments = ComRepository.getComments(page)
                _comState.update { it.copy(comments = comments, isLoading = false) }
            }catch (e:Exception){
                _comState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
        }
    }




/*
class UserViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
