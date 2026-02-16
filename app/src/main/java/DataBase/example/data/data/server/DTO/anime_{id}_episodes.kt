package DataBase.example.data.data.server.DTO

data class AnimeEpisodesResponse(
    val data: List<EpisodeDto>,
    val pagination: PaginationDto
)

data class EpisodeDto(
    val mal_id: Int,
    val title: String?,
    val title_japanese: String?,
    val aired: String?,
    val score: Double?,
    val filler: Boolean?,
    val recap: Boolean?
)

data class PaginationDto(
    val last_visible_page: Int,
    val has_next_page: Boolean
)
