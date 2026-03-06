package data.viewModel.server

import data.domain.repository.AnimeRepository
import data.domain.state.server.AnimeEpisodeDetailUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



class EpisodeViewModel @Inject constructor( val repository: AnimeRepository): ViewModel(){
    val _state= MutableStateFlow(AnimeEpisodeDetailUiState())
    val state=_state.asStateFlow()

    private fun loadEpisodeDetail(id:Int,episodeNum:Int){
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch{
            repository.getAnimeEpisodeDetail(id,episodeNum).fold(
                onSuccess = {response->
                    _state.update { it.copy(episodes=response.data,
                        isLoading = false) }
                },
                onFailure = {throwable->
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

}