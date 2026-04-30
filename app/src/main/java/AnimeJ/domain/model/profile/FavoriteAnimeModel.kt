package AnimeJ.domain.model.profile

import AnimeJ.domain.model.server.ImagesModel
import AnimeJ.domain.model.server.NameModel
import AnimeJ.domain.model.server.TrailerModel
import AnimeJ.presentation.screens.components.AnimeStatus
import androidx.room.PrimaryKey




data class FavoriteAnimeModel(
    val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?,
    val episodes: Int?,
    val rank: Int?,
    val members: Int?,
    val type: String?,
    val rating: String?,
    val genres: List<NameModel>?,
    val status: AnimeStatus = AnimeStatus.NONE
)

