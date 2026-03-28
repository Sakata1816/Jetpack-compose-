package data.domain.state.local

import androidx.room.Query
import data.domain.model.local.FavoriteAnimeModel
import kotlinx.coroutines.flow.Flow

data class FavouriteAnimeUiState(
    val users: List<FavoriteAnimeModel> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
    )