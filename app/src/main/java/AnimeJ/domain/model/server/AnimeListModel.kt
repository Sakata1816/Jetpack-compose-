package AnimeJ.domain.model.server

data class AnimeResponseModel(
    val pagination: PaginationAnimeModel,
    val data: List<AnimeDetailModel>
)

data class PaginationAnimeModel(
    val last_visible_page: Int?,
    val has_next_page: Boolean?,
    val current_page: Int?,
    val items: PaginationItemsModel?
)

data class PaginationItemsModel(
    val count: Int?,
    val total: Int?,
    val per_page: Int?
)