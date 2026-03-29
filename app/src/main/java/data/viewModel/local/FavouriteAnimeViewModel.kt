package data.viewModel.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeRepositoryImpl
import data.domain.model.local.FavoriteAnimeModel
import data.screens.components.AnimeStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteAnimeViewModel @Inject constructor(
    private val repository: AnimeRepositoryImpl
) : ViewModel() {

    // 🔍 поиск
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    // 🎯 фильтр по статусу
    private val _statusFilter = MutableStateFlow<AnimeStatus?>(AnimeStatus.WATCHING)
    val statusFilter = _statusFilter.asStateFlow()

    val favorites = repository.getAllAnime("")


    // 📦 главный поток списка
    val getFavourite = combine(
        repository.getAllAnime(""), // 👈 Flow<List<Anime>>
        _searchQuery,
        _statusFilter
    ) { list, query, status ->

        list.filter { anime ->

            val matchesStatus =
                status == null || anime.status == status

            val matchesQuery =
                query.isBlank() || anime.title.contains(query, ignoreCase = true)

            matchesStatus && matchesQuery
        }

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )



    fun onSearchChange(query: String) {
        _searchQuery.value = query
    }

    fun onStatusFilterChange(status: AnimeStatus?) {
        _statusFilter.value = status
    }

    // 🔥 обновление статуса


    fun changeStatus(
        anime: FavoriteAnimeModel,
        status: AnimeStatus
    ) {
        viewModelScope.launch {
            if (status == AnimeStatus.NONE || status == AnimeStatus.DELETED) {
                repository.deleteAnime(anime.mal_id)
            } else {
                repository.insertAnime(
                    anime.copy(status = status)
                )
            }
        }
    }
}