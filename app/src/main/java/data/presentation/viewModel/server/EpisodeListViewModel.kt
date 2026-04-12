package data.presentation.viewModel.server

import data.presentation.state.server.AnimeEpisodesUiState
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
class EpisodeListViewModel  @Inject constructor(
    private val repository: AnimeRepositoryImpl
): ViewModel(){
    private val _state= MutableStateFlow(AnimeEpisodesUiState())
    val state=_state.asStateFlow()
    private var isLoadingPage = false


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