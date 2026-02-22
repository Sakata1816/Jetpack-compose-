package DataBase.example.data.viewModel

import DataBase.example.data.data.repository.AnimeRepositoryImpl
import DataBase.example.data.domain.state.server.AnimeListUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val repository: AnimeRepositoryImpl
) : ViewModel(){
    private val _state = MutableStateFlow(AnimeListUiState())
    val state: StateFlow<AnimeListUiState> = _state.asStateFlow()


    private fun loadAnimeList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) } // включаем загрузку

            repository.getAllAnimeList().fold(
                onSuccess = { response ->
                    _state.update { it.copy(
                            anime = response.data, // данные из репо
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


