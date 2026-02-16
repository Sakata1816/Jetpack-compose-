package DataBase.example.data.data.server.DTO

data class AnimeFullResponse(
    val data: AnimeFullDto
)

data class AnimeFullDto(
    val mal_id: Int,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val synopsis: String?,
    val images: ImagesDto,

    val score: Double?,
    val rank: Int?,
    val popularity: Int?,
    val episodes: Int?,
    val status: String?,
    val year: Int?,
    val season: String?,

    val trailer: TrailerDto?,
    val genres: List<NameDto>,
    val studios: List<NameDto>,
    val producers: List<NameDto>
)

data class ImagesDto(
    val jpg: JpgDto
)

data class JpgDto(
    val image_url: String?,
    val large_image_url: String?
)

data class TrailerDto(
    val url: String?,
    val youtube_id: String?
)

data class NameDto(
    val mal_id: Int,
    val name: String
)