package data.viewModel.server

import data.domain.repository.AnimeRepository
import data.domain.state.server.AnimeEpisodesUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel  @Inject constructor(
    private val repository: AnimeRepositoryImpl
): ViewModel(){
    private val _state= MutableStateFlow(AnimeEpisodesUiState())
    val state=_state.asStateFlow()
    private var isLoadingPage = false



    // 🔹 Первая загрузка
 /*   fun loadFirstPage(animeId: Int) {
        _state.value = AnimeEpisodesUiState(isLoading = true)

        viewModelScope.launch {
            repository.getAnimeEpisodes(animeId, page = 1)
                .fold(
                    onSuccess = { response ->
                        _state.update {
                            it.copy(
                                episode = response.episodes,
                                isLoading = false,
                                currentPage = 2,
                                endReached = !response.pagination.hasNextPage
                            )
                        }
                    },
                    onFailure = { throwable ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = throwable.message
                            )
                        }
                    }
                )
        }
    }

    // 🔹 Загрузка следующей страницы
    fun loadNextPage(animeId: Int) {

        val currentState = _state.value

        if (currentState.isNextPageLoading || currentState.endReached) return

        viewModelScope.launch {

            _state.update { it.copy(isNextPageLoading = true) }

            repository.getAnimeEpisodes(animeId, currentState.currentPage)
                .fold(
                    onSuccess = { response ->

                        _state.update { oldState ->
                            oldState.copy(
                                episode = oldState.episode + response.episodes,
                                currentPage = oldState.currentPage + 1,
                                endReached = !response.pagination.hasNextPage,
                                isNextPageLoading = false
                            )
                        }
                    },
                    onFailure = { throwable ->
                        _state.update {
                            it.copy(
                                isNextPageLoading = false,
                                error = throwable.message
                            )
                        }
                    }
                )
        }
    }
*/

    fun loadAnimeEpisodes(id:Int){
        val uiState = _state.value

        if (!uiState.hasNextPage || isLoadingPage) return
        viewModelScope.launch {
            isLoadingPage=true
            _state.update { it.copy(isLoading = true, error = null) }
            repository.getAnimeEpisodes(id,uiState.currentPage).fold(
                onSuccess = {response->
                    val newList = uiState.episode + response.episodes
                    val nextPage = (response.pagination.lastVisiblePage?: uiState.currentPage) + 1
                    val hasNext = response.pagination.hasNextPage?:false
                    _state.update { it.copy(isLoading = false,
                        episode =newList,
                        hasNextPage = hasNext,
                        currentPage = nextPage)
                    }
                    isLoadingPage = false
                },
                onFailure = {throwable ->
                    _state.update { it.copy(isLoading = false,
                        error = throwable.message?:"unknown error") }
                    isLoadingPage = false
                }
            )
        }
    }
}