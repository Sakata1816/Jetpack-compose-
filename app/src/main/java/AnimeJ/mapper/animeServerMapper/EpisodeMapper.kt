package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.AnimeEpisodesResponse
import AnimeJ.data.remote.jikan.DTO.EpisodeDto
import AnimeJ.data.remote.jikan.DTO.PaginationDto
import AnimeJ.domain.model.server.AnimeEpisodesModel
import AnimeJ.domain.model.server.EpisodeModel
import AnimeJ.domain.model.server.PaginationModel

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
