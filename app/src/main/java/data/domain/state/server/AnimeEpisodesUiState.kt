package data.domain.state.server

import data.domain.model.server.EpisodeModel


data class AnimeEpisodesUiState (
    val episode: List<EpisodeModel> = emptyList(),
    val isLoading: Boolean = false,          // первая загрузка
    val isNextPageLoading: Boolean = false,  // догрузка
    val currentPage: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null
)