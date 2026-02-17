package DataBase.example.data.domain.model.server

data class AnimeDetailModel(
    val id: Int,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val synopsis: String?,
    val imageUrl: String?,        // основная картинка
    val score: Double?,
    val rank: Int?,
    val popularity: Int?,
    val episodes: Int?,
    val status: String?,
    val year: Int?,
    val season: String?,
    val trailerUrl: String?,
    val trailerYoutubeId: String?,
    val producers: List<String>,
    val studios: List<String>,
    val genres: List<String>
)
