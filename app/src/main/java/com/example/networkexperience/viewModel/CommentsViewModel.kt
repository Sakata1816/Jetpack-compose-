package com.example.networkexperience.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkexperience.Repositories.CommentRepository
import com.example.networkexperience.data.UserUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.minusAssign
import kotlin.collections.plusAssign
import kotlin.compareTo

@HiltViewModel
class CommentsViewModel(@Inject private val repository: CommentRepository) : ViewModel() {

    var page by mutableStateOf(1)
        private set


    private val _comState=MutableStateFlow(UserUIState())

    val comState= _comState.asStateFlow()

    fun nextPage() {
        page += 1
        loadComments()
    }
    fun prevPage() {
        if(page > 0) page -= 1
        loadComments()
    }

    init {
        loadComments()
    }
    private fun loadComments() {
        viewModelScope.launch {
            _comState.update { it.copy(isLoading = true, error = null) }
            try {
                val comments = repository.getComments(page)
                _comState.update { it.copy(comments = comments, isLoading = false) }
            }catch (e:Exception){
                _comState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

}
