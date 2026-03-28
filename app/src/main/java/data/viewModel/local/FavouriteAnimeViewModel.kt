package data.viewModel.local

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Path.Companion.combine
import data.domain.model.local.FavoriteAnimeModel
import data.domain.repository.AnimeRepository
import data.domain.state.local.FavouriteAnimeUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeRepositoryImpl
import data.data.repository.AnimeRepositoryImpl_Factory
import data.screens.components.AnimeStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteAnimeViewModel @Inject constructor(val repository: AnimeRepositoryImpl): ViewModel() {

    private val _state= MutableStateFlow(FavouriteAnimeUiState())
    val state: StateFlow<FavouriteAnimeUiState> = _state.asStateFlow()

    fun loadFav(){
        val uiState=state.value
        viewModelScope.launch {
            repository.getAllAnime(uiState.searchQuery).collect {list->
                _state.update { it.copy(users = list) }
            }

        }

    }

    fun onSearchChange(query: String){
        _state.update { it.copy(
            searchQuery = query
        ) }
    }




    // добавить
    fun insertAnime(anime: FavoriteAnimeModel) {
        viewModelScope.launch {
            repository.insertAnime(anime)
        }
    }

    // удалить
    fun deleteAnime(id: Int) {
        viewModelScope.launch {
            repository.deleteAnime(id)
        }
    }

    // проверить (если нужно)
    suspend fun isFavorite(id: Int): Boolean {
        return repository.isFavorite(id)
    }


}
