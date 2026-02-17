package DataBase.example.data.domain.model.local

data class FavoriteAnimeModel(
    val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?
)