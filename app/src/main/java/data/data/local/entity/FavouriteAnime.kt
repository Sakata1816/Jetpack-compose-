package data.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class FavoriteAnimeEntity(
    @PrimaryKey val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?
)
