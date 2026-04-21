package AnimeJ.presentation.state.local

import AnimeJ.domain.model.profile.FavoriteAnimeModel

data class FavouriteAnimeUiState(
    val users: List<FavoriteAnimeModel> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
    )