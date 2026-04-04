package data.presentation.state.server

import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.AnimeFullModel

data class AnimeFullUiState(
    val anime: AnimeFullModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)