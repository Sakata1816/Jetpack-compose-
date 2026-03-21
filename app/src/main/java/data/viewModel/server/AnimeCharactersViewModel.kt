package data.viewModel.server

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeRepositoryImpl
import data.domain.state.server.AnimeCharactersUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeCharactersViewModel @Inject constructor(val repository: AnimeRepositoryImpl ): ViewModel(){
    val _state= MutableStateFlow(AnimeCharactersUiState())
    val state=_state.asStateFlow()


    fun loadCharacters(id: Int){
        viewModelScope.launch {
            val charactersDeferred = async {
                repository.getAnimeCharacters(id)
            }

            val charactersResult = charactersDeferred.await()

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