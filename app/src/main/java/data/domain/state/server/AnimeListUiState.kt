package data.domain.state.server

import data.domain.model.server.AnimeDetailModel
import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.AnimeResponseModel
data class AnimeListUiState(
    val isLoading: Boolean = false,
    val anime: List<AnimeDetailModel> = emptyList(),
    val error: String? = null,
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true
)