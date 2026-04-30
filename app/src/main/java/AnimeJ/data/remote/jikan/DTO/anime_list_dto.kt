package AnimeJ.data.remote.jikan.DTO

data class AnimeResponse(
    val pagination: PaginationAnimeDto?,
    val data: List<AnimeDetailDto>
)

data class PaginationAnimeDto(
    val last_visible_page: Int?,
    val has_next_page: Boolean?,
    val current_page: Int?,
    val items: PaginationItemsDto?
)

data class PaginationItemsDto(
    val count: Int?,
    val total: Int?,
    val per_page: Int?
)