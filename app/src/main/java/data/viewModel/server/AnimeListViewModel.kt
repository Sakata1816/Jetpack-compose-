package data.viewModel.server

import data.data.repository.AnimeRepositoryImpl
import data.domain.state.server.AnimeListUiState
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
    private var isLoadingPage = false


init {
    loadAnimeList()
}

    fun loadAnimeList() {
        val uiState = _state.value

        if (!uiState.hasNextPage || isLoadingPage) return
        viewModelScope.launch {
            isLoadingPage = true
            _state.update { it.copy(isLoading = true, error = null) } // включаем загрузку

            repository.getAllAnimeList(uiState.currentPage).fold(
                onSuccess = { response ->
                    val newList = uiState.anime + response.data
                    val nextPage = (response.pagination.current_page ?: uiState.currentPage) + 1
                    val hasNext = response.pagination.has_next_page ?: false
                    _state.update { it.copy(
                            anime = newList,
                            isLoading = false,
                        currentPage = nextPage,
                        hasNextPage = hasNext
                        )
                    }
                },
                onFailure = { throwable ->
                    _state.update { it.copy(
                            error = throwable.message ?: "Unknown error",
                            isLoading = false
                        )
                    }
                    isLoadingPage = false
                }
            )
        }
    }

    }


