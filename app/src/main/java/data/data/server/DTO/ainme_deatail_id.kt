package data.data.server.DTO


data class AnimeResponse(val data: List<AnimeDetailDto>)
data class AnimeDetailResponse(val data: AnimeDetailDto)

data class AnimeDetailDto(
    val mal_id: Int,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val synopsis: String?,
    val images: ImagesDto?,
    val score: Double?,
    val rank: Int?,
    val popularity: Int?,
    val episodes: Int?,
    val status: String?,
    val year: Int?,
    val season: String?,
    val producers: List<NameDto> = emptyList(),
    val studios: List<NameDto> = emptyList(),
    val genres: List<NameDto> = emptyList(),
    val trailer: TrailerDto? = null
)


