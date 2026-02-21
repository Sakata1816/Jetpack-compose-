package DataBase.example.data.mapper.animeServerMapper

import DataBase.example.data.data.server.DTO.AnimeEpisodesResponse
import DataBase.example.data.data.server.DTO.EpisodeDto
import DataBase.example.data.data.server.DTO.PaginationDto
import DataBase.example.data.domain.model.server.AnimeEpisodesModel
import DataBase.example.data.domain.model.server.EpisodeModel
import DataBase.example.data.domain.model.server.PaginationModel

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
