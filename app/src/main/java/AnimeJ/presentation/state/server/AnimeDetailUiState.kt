package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.domain.model.server.CharacterItemModel

data class AnimeDetailUiState(
    val characters: List<CharacterItemModel> = emptyList(),
    val anime: AnimeDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)