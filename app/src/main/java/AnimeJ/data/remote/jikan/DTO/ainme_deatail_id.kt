package AnimeJ.data.remote.jikan.DTO


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
    val producers: List<NameDto>?,
    val studios: List<NameDto>?,
    val genres: List<NameDto>?,
    val trailer: TrailerDto?,
    val duration: String?,
    val rating: String?,
    val type: String?,
    val source: String?,
    val aired: AiredDto?,
    val members: Int?,
    val favorites: Int?,
    val scoredBy: Int?,
    val themes: List<NameDto>?,
    val demographics: List<NameDto>?
)

data class AiredDto(
    val from: String?,
    val to: String?,
    val prop:PropDto?
)

data class PropDto(
    val from: DateParts?,
    val to: DateParts?
)

data class DateParts(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

