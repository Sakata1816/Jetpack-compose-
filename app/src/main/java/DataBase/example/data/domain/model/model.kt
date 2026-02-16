package DataBase.example.data.domain.model

import DataBase.example.data.data.local.dao.FavoriteAnimeDao

data class UserState(
    val users: List<FavoriteAnime> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
