package data.mapper.animeServerMapper

import data.data.server.jikan.DTO.AnimeEpisodeDetailResponse
import data.data.server.jikan.DTO.EpisodeDetailDto
import data.domain.model.server.EpisodeDetailModel
import data.domain.model.server.EpisodeDetailResponseModel

fun EpisodeDetailDto.toModel(): EpisodeDetailModel {
    return EpisodeDetailModel(
        id = mal_id,
        title = title,
        titleJapanese = title_japanese,
        titleRomanji = title_romanji,
        duration = duration,
        aired = aired,
        isFiller = filler ?: false, // если null, считаем false
        isRecap = recap ?: false,   // если null, считаем false
        synopsis = synopsis,
        url = url
    )
}

fun AnimeEpisodeDetailResponse.toModel(): EpisodeDetailResponseModel {
    return EpisodeDetailResponseModel(
        data = data.toModel()
    )
}
