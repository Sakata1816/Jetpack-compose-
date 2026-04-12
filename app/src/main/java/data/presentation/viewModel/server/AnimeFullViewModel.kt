package data.presentation.viewModel.server

import data.presentation.state.server.AnimeFullUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.server.AnimeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class AnimeFullViewModel @Inject constructor(
    private val repository: AnimeRepositoryImpl
): ViewModel(){
    private val _state= MutableStateFlow(AnimeFullUiState())
    val state=_state.asStateFlow()

    private fun loadFullAnimeInfo(id:Int){
        viewModelScope.launch {
            _state.update { it.copy( isLoading = true, error = null) }

            repository.getAnimeFullInfo(id).fold(
                onSuccess = { response ->
                    _state.update { it.copy(
                        anime = response, // данные из репо
                        isLoading = false
                    )
                    }
                },
                onFailure = { throwable ->
                    _state.update { it.copy(
                        error = throwable.message ?: "Unknown error",
                        isLoading = false
                    )
                    }
                }
            )
        }
    }
}