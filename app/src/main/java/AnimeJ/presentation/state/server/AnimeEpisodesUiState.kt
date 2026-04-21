package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.EpisodeModel


data class AnimeEpisodesUiState (
    val episode: List<EpisodeModel> = emptyList(),
    val isLoading: Boolean = false,          // первая загрузка
    val lastVisiblePage:Int = 1,  // догрузка
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true,
    val error: String? = null
)