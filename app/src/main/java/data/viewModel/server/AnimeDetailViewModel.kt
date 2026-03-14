package data.viewModel.server

import androidx.compose.ui.util.fastCbrt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeRepositoryImpl
import data.domain.repository.AnimeRepository
import data.domain.state.server.AnimeDetailUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.connection.Exchange
import javax.inject.Inject


@HiltViewModel
class AnimeDetailViewModel @Inject constructor( val repository: AnimeRepositoryImpl): ViewModel() {
    val _state = MutableStateFlow(AnimeDetailUiState())
    val state=_state.asStateFlow()

    fun loadAnime(id: Int){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
                val animeDeferred = async {
                    repository.getAnimeInfo(id)
                }

                val charactersDeferred = async {
                    repository.getAnimeCharacters(id)
                }
                val animeResult = animeDeferred.await()
                val charactersResult = charactersDeferred.await()

            animeResult.fold(
                onSuccess = {response->
                    _state.update { it.copy(anime=response.data) }
                },
                onFailure = {throwable ->
                    _state.update { it.copy(error=throwable.message?:"unknown error") }
                }
            )
            charactersResult.fold(
                onSuccess = {response->
                    _state.update { it.copy(characters=response.data,
                        isLoading = false) }
                },
                onFailure = {throwable ->
                    _state.update { it.copy(error=throwable.message?:"unknown error", isLoading = false) }
                }
            )


        }
    }

}