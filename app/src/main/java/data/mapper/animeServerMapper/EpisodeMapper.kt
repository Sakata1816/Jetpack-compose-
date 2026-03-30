package data.mapper.animeServerMapper

import data.data.server.jikan.DTO.AnimeEpisodesResponse
import data.data.server.jikan.DTO.EpisodeDto
import data.data.server.jikan.DTO.PaginationDto
import data.domain.model.server.AnimeEpisodesModel
import data.domain.model.server.EpisodeModel
import data.domain.model.server.PaginationModel

fun EpisodeDto.toModel(): EpisodeModel {
    return EpisodeModel(
        id = mal_id,
        title = title,
        titleJapanese = title_japanese,
        aired = aired,
        score = score,
        isFiller = filler ?: false,
        isRecap = recap ?: false
    )
}

fun PaginationDto.toModel(): PaginationModel {
    return PaginationModel(
        lastVisiblePage = last_visible_page,
        hasNextPage = has_next_page
    )
}

fun AnimeEpisodesResponse.toModel(): AnimeEpisodesModel {
    return AnimeEpisodesModel(
        episodes = data.map { it.toModel() },
        pagination = pagination.toModel()
    )
}
