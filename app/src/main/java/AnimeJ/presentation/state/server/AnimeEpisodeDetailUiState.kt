package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.EpisodeDetailModel

data class AnimeEpisodeDetailUiState(
    val episodes: EpisodeDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)