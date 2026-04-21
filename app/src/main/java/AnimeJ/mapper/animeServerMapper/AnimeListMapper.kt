package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.AnimeResponse
import AnimeJ.data.remote.jikan.DTO.PaginationAnimeDto
import AnimeJ.data.remote.jikan.DTO.PaginationItemsDto
import AnimeJ.domain.model.server.AnimeResponseModel
import AnimeJ.domain.model.server.PaginationAnimeModel
import AnimeJ.domain.model.server.PaginationItemsModel
import kotlin.collections.map


fun AnimeResponse.toModel(): AnimeResponseModel{
    return AnimeResponseModel(
        pagination=pagination.toModel(),
        data=data.map { it.toModel() }
    )
}


fun PaginationAnimeDto.toModel(): PaginationAnimeModel{
    return PaginationAnimeModel(
        last_visible_page=last_visible_page,
        has_next_page=has_next_page,
        current_page=current_page,
        items=items?.toModel()
    )
}

fun PaginationItemsDto.toModel(): PaginationItemsModel{
    return PaginationItemsModel(
        count=count,
        total=total,
        per_page=per_page
    )
}