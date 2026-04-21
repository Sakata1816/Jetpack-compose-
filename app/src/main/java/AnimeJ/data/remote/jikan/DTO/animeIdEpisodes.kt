package AnimeJ.data.remote.jikan.DTO

data class AnimeEpisodesResponse(
    val data: List<EpisodeDto>,
    val pagination: PaginationDto
)

data class EpisodeDto(
    val mal_id: Int,
    val title: String?,
    val title_japanese: String?,
    val title_romanji: String?,
    val aired: String?,
    val score: Double?,
    val filler: Boolean?,
    val recap: Boolean?,
    val url: String?
)

data class PaginationDto(
    val last_visible_page: Int,
    val has_next_page: Boolean
)
