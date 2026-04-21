package AnimeJ.domain.model.profile

import AnimeJ.presentation.screens.components.AnimeStatus

data class FavoriteAnimeModelProfile(
    val mal_id: Int=0,
    val title: String="",
    val imageUrl: String? = null,
    val score: Double? = null,
    val status: AnimeStatus = AnimeStatus.NONE
)


data class FavoriteAnimeModel(
    val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?,
    var status: AnimeStatus= AnimeStatus.NONE
)