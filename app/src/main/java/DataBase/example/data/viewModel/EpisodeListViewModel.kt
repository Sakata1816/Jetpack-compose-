package DataBase.example.data.viewModel

import DataBase.example.data.domain.repository.AnimeRepository
import DataBase.example.data.domain.state.server.AnimeEpisodesUiState
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

    fun LoadEpisode(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

        }
    }
}