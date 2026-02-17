package DataBase.example.data.domain.model

import DataBase.example.data.domain.model.local.FavoriteAnime

data class UserState(
    val users: List<FavoriteAnime> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
