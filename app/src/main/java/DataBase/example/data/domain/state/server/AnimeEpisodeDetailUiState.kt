package DataBase.example.data.domain.state.server

import DataBase.example.data.domain.model.server.EpisodeDetailModel
import DataBase.example.data.domain.model.server.EpisodeDetailResponseModel

data class AnimeEpisodeDetailUiState(
    val episodes: EpisodeDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)