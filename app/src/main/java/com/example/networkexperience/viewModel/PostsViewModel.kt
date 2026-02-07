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
import okhttp3.Request
import javax.inject.Inject
import kotlin.jvm.java




@HiltViewModel
class UserViewModel @Inject constructor(private val PostRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(UserUIState())
    val state = _state.asStateFlow()
/*    var id by mutableStateOf(1)
        private set*/


    init {
        loadUsers()
    }


   fun getPostsId(id:Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val post = PostRepository.getPosts(id)
                if(post.isSuccessful){
                    val postBody = post.body()
                    _state.update { it.copy(post = postBody, isLoading = false) }
                }else{
                    val errorCode = post.code()
                    val errorBody = post.errorBody()?.string()
                    println("Ошибка! Код: $errorCode, тело ошибки: $errorBody")
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
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

    fun loadPostsComments(id:Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, error = null)
            }
            try {
                val postsComment = PostRepository.getPostsComments(id)
                _state.update {
                    it.copy(comments = postsComment, isLoading = false)
                }
            }catch(e: Exception) {
                _state.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }


    fun postPosts(request: Post) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
try{
    val response = PostRepository.postPost(request)
    if (response.isSuccessful) {
        val createdPost = response.body()
        _state.update {
            it.copy(posts = it.posts + listOf(createdPost!!), isLoading = false)
        }
}else{
    val errorCode = response.code()
    val errorBody = response.errorBody()?.string()
    println("Ошибка! Код: $errorCode, тело ошибки: $errorBody")
    }
}catch (e: Exception) {
    _state.update { it.copy(error = e.message, isLoading = false) }
}
        }
    }


    fun putPosts(postId: Int, request: Post) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val response = PostRepository.putPosts(postId, request)
                if (response.isSuccessful) {
                    val updatedPost = response.body()

                    if (updatedPost != null) {
                        _state.update { currentState ->
                            val newPosts = currentState.posts.map { post ->
                                if (post.id == postId) updatedPost else post
                            }
                            currentState.copy(posts = newPosts, isLoading = false)
                        }
                    } else {
                        _state.update { it.copy(isLoading = false) }
                    }
                }else{
                    val errorCode = response.code()
                    val errorBody = response.errorBody()?.string()
                    println("Ошибка! Код: $errorCode, тело ошибки: $errorBody")
                }
            }catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
        }


    fun patchPosts(postId: Int, request: Map<String, Any>) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val response = PostRepository.patchPosts(postId, request)

                if (response.isSuccessful) {
                    val updatedPost = response.body()

                    _state.update { currentState ->
                        val newPosts = if (updatedPost != null) {
                            currentState.posts.map { post ->
                                if (post.id == updatedPost.id) updatedPost else post
                            }
                        } else {
                            currentState.posts
                        }
                        currentState.copy(posts = newPosts, isLoading = false)
                    }

                } else {
                    val errorMsg = "Ошибка ${response.code()}: ${response.errorBody()?.string()}"
                    _state.update { it.copy(error = errorMsg, isLoading = false) }
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e.message ?: "Неизвестная ошибка", isLoading = false)
                }
            }
        }
    }


    fun deletePosts(postId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val deletedPost = PostRepository.deletePosts(postId)
                _state.update { currentState ->
                    val newPosts = currentState.posts.filter { it.id != postId }
                    currentState.copy(posts = newPosts, isLoading = false)
                }
            }catch (e: Exception){
                _state.update { it.copy(error = e.message, isLoading = false) }
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
