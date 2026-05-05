package AnimeJ.data.local.entity

import AnimeJ.domain.model.server.NameModel
import androidx.room.Entity
import androidx.room.PrimaryKey
import AnimeJ.presentation.screens.components.AnimeStatus

@Entity(tableName = "favorite_anime",
    primaryKeys = ["mal_id"]
)
data class FavoriteAnimeEntity(
    val mal_id: Int,
    val userId: String,
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

