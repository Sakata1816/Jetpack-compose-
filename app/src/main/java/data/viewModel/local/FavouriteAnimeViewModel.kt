package data.viewModel.local

import data.domain.model.local.FavoriteAnimeModel
import data.domain.repository.AnimeRepository
import data.domain.state.local.FavouriteAnimeUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeRepositoryImpl
import data.data.repository.AnimeRepositoryImpl_Factory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteAnimeViewModel @Inject constructor(val repository: AnimeRepositoryImpl): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val favourites = _searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                repository.getAllAnime()
            } else {
                repository.searchAnime(query)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun onSearchChange(query: String) {
        _searchQuery.value = query
    }

    fun isFavourite(anime: FavoriteAnimeModel){
        viewModelScope.launch {
            repository.isFavorite(anime)
        }
    }

    // добавить
    fun insertAnime(anime: FavoriteAnimeModel) {
        viewModelScope.launch {
            repository.insertAnime(anime)
        }
    }

    // удалить
    fun deleteAnime(anime: FavoriteAnimeModel) {
        viewModelScope.launch {
            repository.deleteAnime(anime)
        }
    }

    // проверить (если нужно)
    suspend fun isFavorite(anime: FavoriteAnimeModel): Boolean {
        return repository.isFavorite(anime)
    }

}