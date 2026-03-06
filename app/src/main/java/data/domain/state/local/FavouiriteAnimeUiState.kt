package data.domain.state.local

import data.domain.model.local.FavoriteAnimeModel

data class FavouriteAnimeUiState(
    val users: List<FavoriteAnimeModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)