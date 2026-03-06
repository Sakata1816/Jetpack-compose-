package data.viewModel.server

import data.domain.repository.AnimeRepository
import data.domain.state.server.AnimeEpisodesUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeListViewModel  @Inject constructor(
    private val repository: AnimeRepository
): ViewModel(){
    private val _state= MutableStateFlow(AnimeEpisodesUiState())
    private val state=_state.asStateFlow()

    // 🔹 Первая загрузка
    fun loadFirstPage(animeId: Int) {
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
}