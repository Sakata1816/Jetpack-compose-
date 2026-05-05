package AnimeJ.presentation.viewModel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import AnimeJ.data.repository.profile.FavoriteRepositoryImpl
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.domain.repository.profile.FavoriteRepository
import AnimeJ.presentation.screens.components.AnimeStatus
import AnimeJ.presentation.state.profile.FavoriteAnimeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteAnimeViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {


    private val _state= MutableStateFlow(FavoriteAnimeUiState())
    val state = _state.asStateFlow()


    private val _searchQuery=MutableStateFlow("")
    val searchQuery=_searchQuery.asStateFlow()


    init {
        // Синхронизируем _searchQuery со state
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    _state.update { it.copy(searchQuery = query) }
                }
        }

        // Подписываемся на списки по статусу
        observeList(AnimeStatus.WATCHING) { list, s -> s.copy(watchingList = list) }
        observeList(AnimeStatus.COMPLETED) { list, s -> s.copy(completedList = list) }
        observeList(AnimeStatus.DROPPED) { list, s -> s.copy(droppedList = list) }
        observeList(AnimeStatus.PLAN) { list, s -> s.copy(plannedList = list) }
    }



    //поучить эту часть получше
    private fun observeList(
        status: AnimeStatus,
        updater: (List<FavoriteAnimeModel>, FavoriteAnimeUiState) -> FavoriteAnimeUiState
    ) {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query -> repository.getAnimeByStatus(status, query) }
                .collect { list ->
                    _state.update { updater(list, it) }
                }
        }
    }


    val getFavorites = combine(
        repository.getFavorites(_searchQuery.value),
        searchQuery
    ) { list, query ->
        list.filter { anime ->
            query.isBlank() || anime.title.contains(query, ignoreCase = true)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList<FavoriteAnimeModel>()
    )


    fun syncFromFirestore(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            repository.syncFromFirestore().fold(
                onSuccess = {
                    _state.update { it.copy(
                        isLoading = false
                    ) }
                },
                onFailure = {Throwable->
                    _state.update { it.copy(
                    isLoading = false,
                    error = Throwable.message?:"error on sync..")
                    }
                }
            )
        }
    }


    fun setSearch(query: String){
        _state.update { it.copy(
            searchQuery = query
        ) }
    }


    fun changeStatus(
        anime: FavoriteAnimeModel,
        status: AnimeStatus
    ){
        viewModelScope.launch {
           if(status== AnimeStatus.NONE || status == AnimeStatus.DELETED){
                repository.deleteAnime(anime.mal_id)
            }else{
                repository.addAnime(anime.copy(
                    status=status)
                )
            }
        }
    }
}

