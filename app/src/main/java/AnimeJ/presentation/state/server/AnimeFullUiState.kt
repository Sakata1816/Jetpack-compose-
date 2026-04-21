package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.AnimeFullModel

data class AnimeFullUiState(
    val anime: AnimeFullModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)