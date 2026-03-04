package DataBase.example.data.viewModel.local

import DataBase.example.data.domain.model.local.FavoriteAnimeModel
import DataBase.example.data.domain.repository.AnimeRepository
import DataBase.example.data.domain.state.local.FavouriteAnimeUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteAnimeViewModel @Inject constructor(val repository: AnimeRepository): ViewModel() {
    val _state= MutableStateFlow(FavouriteAnimeUiState())
    val state=_state.asStateFlow()

    fun loadFavouriteAnime(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            repository.getAllAnime().fold(
                onSuccess = { response->
                    _state.update { it.copy(isLoading = false,
                        users = response) }},
                onFailure = {throwable ->
                    _state.update { it.copy(isLoading = false,
                        error = throwable.message) }
                }
            )

        }
    }

    fun insertAnime(anime: FavoriteAnimeModel){
        viewModelScope.launch {
            _state.update { it.copy( isLoading = true) }
            repository.insertAnime(anime).fold(
                onSuccess = {response->
                    _state.update { it.copy(isLoading = false)
                    }
                },
                onFailure = {throwable ->
                    _state.update { it.copy(isLoading = false, error = throwable.message) }
                }
            )

        }
    }



}