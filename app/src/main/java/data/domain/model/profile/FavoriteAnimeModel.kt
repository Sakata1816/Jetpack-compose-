package data.domain.model.profile

import data.presentation.screens.components.AnimeStatus

data class FavoriteAnimeModel(
    val mal_id: Int=0,
    val title: String="",
    val imageUrl: String? = null,
    val score: Double? = null,
    val status: AnimeStatus = AnimeStatus.NONE
)