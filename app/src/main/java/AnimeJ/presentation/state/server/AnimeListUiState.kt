package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.AnimeDetailModel

data class AnimeListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val anime: List<AnimeDetailModel> = emptyList(),
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true,
    val searchQuery: String = ""
)