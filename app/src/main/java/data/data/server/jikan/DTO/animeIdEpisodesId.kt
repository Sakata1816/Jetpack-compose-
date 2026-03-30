package data.data.server.jikan.DTO

data class AnimeEpisodeDetailResponse(
    val data: EpisodeDetailDto
)

data class EpisodeDetailDto(
    val mal_id: Int,
    val title: String?,
    val title_japanese: String?,
    val title_romanji: String?,
    val duration: Int?,
    val aired: String?,
    val filler: Boolean?,
    val recap: Boolean?,
    val synopsis: String?,
    val url: String?
)
