package DataBase.example.data.domain.state.server

import DataBase.example.data.domain.model.server.AnimeDetailModel
import DataBase.example.data.domain.model.server.AnimeDetailResponseModel
import DataBase.example.data.domain.model.server.CharacterItemModel

data class AnimeDetailUiState(
    val characters: List<CharacterItemModel> = emptyList(),
    val anime: AnimeDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)