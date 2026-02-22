package DataBase.example.data.domain.state.server

import DataBase.example.data.domain.model.server.AnimeDetailResponseModel
import DataBase.example.data.domain.model.server.AnimeFullModel

data class AnimeFullUiState(
    val anime: AnimeFullModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)