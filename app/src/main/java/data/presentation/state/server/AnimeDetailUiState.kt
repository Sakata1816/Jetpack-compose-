package data.presentation.state.server

import data.domain.model.server.AnimeDetailModel
import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.CharacterItemModel

data class AnimeDetailUiState(
    val characters: List<CharacterItemModel> = emptyList(),
    val anime: AnimeDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)