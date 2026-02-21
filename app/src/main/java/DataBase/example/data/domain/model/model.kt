package DataBase.example.data.domain.model

import DataBase.example.data.domain.model.local.FavoriteAnimeModel

data class UserState(
    val users: List<FavoriteAnimeModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
