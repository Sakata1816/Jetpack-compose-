package data.domain.model.local

import data.screens.components.AnimeStatus

data class FavoriteAnimeModel(
    val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?,
    val status: AnimeStatus
)