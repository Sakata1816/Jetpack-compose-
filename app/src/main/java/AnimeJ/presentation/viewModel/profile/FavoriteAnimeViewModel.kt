package AnimeJ.presentation.viewModel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import AnimeJ.data.repository.profile.FavoriteRepositoryImpl
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.presentation.screens.components.AnimeStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteAnimeViewModel @Inject constructor(
    private val repository: FavoriteRepositoryImpl
): ViewModel() {
 /*   private val _state= MutableStateFlow(FavoriteAnimeUiState())
    val state=_state.asStateFlow()*/

    private val _searchQuery=MutableStateFlow("")
    val searchQuery=_searchQuery.asStateFlow()

    private val _statusFilter= MutableStateFlow<AnimeStatus?>(AnimeStatus.WATCHING)
    val statusFilter=_statusFilter.asStateFlow()


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


    private fun flowByStatus(status: AnimeStatus): StateFlow<List<FavoriteAnimeModel>> =
        _searchQuery
            .debounce(300)
            .distinctUntilChanged()
            .flatMapLatest { query -> repository.getAnimeByStatus(status, query) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val watchingList = flowByStatus(AnimeStatus.WATCHING)
    val completedList = flowByStatus(AnimeStatus.COMPLETED)
    val droppedList = flowByStatus(AnimeStatus.DROPPED)
    val plannedList = flowByStatus(AnimeStatus.PLAN)



/*   val getFavoritesStatus = combine(
        repository.getFavorites(_searchQuery.value),
        searchQuery,
        statusFilter
    ){list,query,status->
        list
            .filter {anime->
                (anime.status == status)
            }
            .filter { anime ->
                query.isBlank() || anime.title.contains(query, ignoreCase = true)
            }

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList<FavoriteAnimeModel>()
    )*/

    fun syncFromFirestore(){
        viewModelScope.launch {
            repository.syncFromFirestore()
        }
    }



    fun setSearch(query: String){
        _searchQuery.value=query
    }


    fun setStatus(status: AnimeStatus){
        _statusFilter.value=status
    }


    fun changeStatus(
        anime: FavoriteAnimeModel,
        status: AnimeStatus
    ){
        viewModelScope.launch {
           val result = if(status== AnimeStatus.NONE || status == AnimeStatus.DELETED){
                repository.deleteAnime(anime.mal_id)
            }else{
                repository.addAnime(anime.copy(
                    status=status
                ))
            }
            /*result.onFailure {
                _state.update { it.copy(error="status change error") }

            }*/
        }
    }
}