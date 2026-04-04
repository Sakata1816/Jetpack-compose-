package data.presentation.viewModel.server

import data.data.repository.AnimeRepositoryImpl
import data.presentation.state.server.AnimeListUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.domain.model.server.AnimeDetailModel
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


    fun onSearchChange(query: String) {
        _state.update {
            it.copy(searchQuery = query)
        }
    }

    fun searchAnime() {
        _state.update {
            it.copy(
                anime = emptyList(),
                currentPage = 1,
                hasNextPage = true
            )
        }

        loadAnimeList()
    }

    fun loadAnimeList() {
        val uiState = _state.value

        if (!uiState.hasNextPage || isLoadingPage) return
        viewModelScope.launch {
            isLoadingPage = true
            _state.update { it.copy(isLoading = true, error = null) } // включаем загрузку

            repository.getAllAnimeList(uiState.currentPage,uiState.searchQuery).fold(
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
                    isLoadingPage = false

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

    fun deleteAnime(anime: AnimeDetailModel) {}

}


