package DataBase.example.data.domain.model

import androidx.room.PrimaryKey

data class FavoriteAnime(
    val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?
)