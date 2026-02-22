package DataBase.example.data.domain.state.server

import DataBase.example.data.domain.model.server.AnimeDetailModel
import DataBase.example.data.domain.model.server.AnimeDetailResponseModel
import DataBase.example.data.domain.model.server.AnimeResponseModel

data class AnimeListUiState (
    val anime: List<AnimeDetailModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
 )