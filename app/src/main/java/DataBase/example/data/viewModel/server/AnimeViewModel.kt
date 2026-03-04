package DataBase.example.data.viewModel.server

import DataBase.example.data.data.repository.AnimeRepositoryImpl
import DataBase.example.data.domain.state.server.AnimeDetailUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject


@HiltViewModel
class AnimeViewModel @Inject constructor( val repository: AnimeRepositoryImpl): ViewModel() {
    private val _state= MutableStateFlow(AnimeDetailUiState())
    val state=_state.asStateFlow()

    private fun loadAnimeInfo(id:Int){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            supervisorScope {
                val animeDeferred=async {
                    repository.getAnimeInfo(id)
                }
                val charactersDeferred=async {
                    repository.getAnimeCharacters(id)
                }

                val animeResult = animeDeferred.await()
                val charactersResult = charactersDeferred.await()

                animeResult.onSuccess { response->
                    _state.update { it.copy(anime=response.data) }
                }
                    .onFailure { throwable ->
                        _state.update { it.copy(error=throwable.message) }
                    }

                charactersResult.onSuccess { response ->
                    _state.update {
                        it.copy(characters = response.data)
                    }
                }.onFailure { throwable ->
                    _state.update {
                        it.copy(error = throwable.message)
                    }
                }

                _state.update { it.copy(isLoading = false) }

            }


        }
    }

}