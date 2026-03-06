package data.domain.state.server

import data.domain.model.server.EpisodeDetailModel
import data.domain.model.server.EpisodeDetailResponseModel

data class AnimeEpisodeDetailUiState(
    val episodes: EpisodeDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)