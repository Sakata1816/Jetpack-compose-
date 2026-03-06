package data.domain.state.server

import data.domain.model.server.AnimeDetailModel
import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.AnimeResponseModel

data class AnimeListUiState (
    val anime: List<AnimeDetailModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
 )