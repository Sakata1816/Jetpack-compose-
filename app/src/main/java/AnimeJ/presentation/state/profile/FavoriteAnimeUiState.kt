package AnimeJ.presentation.state.profile

import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.presentation.screens.components.AnimeStatus


data class FavoriteAnimeUiState(
    val searchQuery: String = "",
    val watchingList: List<FavoriteAnimeModel> = emptyList(),
    val completedList: List<FavoriteAnimeModel> = emptyList(),
    val droppedList: List<FavoriteAnimeModel> = emptyList(),
    val plannedList: List<FavoriteAnimeModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)