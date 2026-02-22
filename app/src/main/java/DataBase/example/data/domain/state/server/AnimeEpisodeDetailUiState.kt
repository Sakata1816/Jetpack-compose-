package DataBase.example.data.domain.state.server

import DataBase.example.data.domain.model.server.EpisodeDetailResponseModel

data class AnimeEpisodeDetailUiState(
    val episodes: EpisodeDetailResponseModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)