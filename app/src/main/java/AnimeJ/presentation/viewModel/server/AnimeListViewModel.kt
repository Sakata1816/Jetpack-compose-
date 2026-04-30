package AnimeJ.presentation.viewModel.server

import AnimeJ.presentation.state.server.AnimeListUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import AnimeJ.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel(){
    private val _state = MutableStateFlow(AnimeListUiState())
    val state: StateFlow<AnimeListUiState> = _state.asStateFlow()
    private var isLoadingPage = false


init {
    loadAnimeList()
    viewModelScope.launch {
        _state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500)
            .drop(1) // пропускаем первый emit (начальный пустой запрос)
            .collect {
                resetAndSearch()
            }
    }

}


    private fun resetAndSearch() {
        _state.update {
            it.copy(
                anime = emptyList(),
                currentPage = 1,
                hasNextPage = true
            )
        }
        isLoadingPage = false
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
                    val nextPage = (response.pagination?.current_page ?: uiState.currentPage) + 1
                    val hasNext = response.pagination?.has_next_page ?: false
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

}


