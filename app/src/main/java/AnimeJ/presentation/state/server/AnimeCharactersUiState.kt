package AnimeJ.presentation.state.server

import AnimeJ.domain.model.server.CharacterItemModel

data class AnimeCharactersUiState(
    val characters: List<CharacterItemModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)