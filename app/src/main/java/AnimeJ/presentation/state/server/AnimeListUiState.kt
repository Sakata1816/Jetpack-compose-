package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.AnimeDetailModel

data class AnimeListUiState(
    val isLoading: Boolean = false,
    val anime: List<AnimeDetailModel> = emptyList(),
    val error: String? = null,
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true,
    val searchQuery: String = ""
)