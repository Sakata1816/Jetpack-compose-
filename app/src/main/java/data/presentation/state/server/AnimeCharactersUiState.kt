package data.presentation.state.server

import data.domain.model.server.CharacterItemModel

data class AnimeCharactersUiState(
    val characters: List<CharacterItemModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)